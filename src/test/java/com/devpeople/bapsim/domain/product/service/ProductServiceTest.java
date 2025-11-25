package com.devpeople.bapsim.domain.product.service;

import com.devpeople.bapsim.domain.product.entity.Product;
import com.devpeople.bapsim.domain.product.repository.ProductRepository;
import com.devpeople.bapsim.domain.store.entity.Store;
import com.devpeople.bapsim.global.exception.CustomException;
import com.devpeople.bapsim.global.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Product testProduct;
    private Store testStore;

    @BeforeEach
    void setUp() {
        // 테스트용 Store 객체 (LAZY 로딩을 위한 연관 객체)
        testStore = Store.builder()
                .id(1L)
                .name("테스트 가게")
                .build();

        // Product Entity의 Builder와 필드에 맞게 객체 생성
        testProduct = Product.builder()
                .id(1L)
                .store(testStore)
                .name("특제 닭볶음탕")
                .description("매콤하고 깊은 맛의 닭볶음탕")
                .price(25000)
                .ratingAvg(BigDecimal.valueOf(4.5))
                .reviewCount(50)
                .isActive(true)
                .isDeleted(false)
                .build();
    }

    @Test
    @DisplayName("ID로 상품 조회 성공")
    void getProductById_Success() {
        // Given: Repository가 ID 1로 조회 시 상품 반환 설정
        given(productRepository.findById(1L)).willReturn(Optional.of(testProduct));

        // When: 서비스 메서드 호출
        Product foundProduct = productService.getProductById(1L);

        // Then: 반환된 상품 정보 검증
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(1L);
        assertThat(foundProduct.getName()).isEqualTo("특제 닭볶음탕");
        verify(productRepository).findById(1L);
    }

    @Test
    @DisplayName("존재하지 않는 ID로 상품 조회 시 ProductNotFoundException 발생")
    void getProductById_NotFound() {
        // Given: Repository가 ID 99로 조회 시 빈 Optional 반환 설정
        given(productRepository.findById(99L)).willReturn(Optional.empty());

        // When & Then: 메서드 호출 시 ProductNotFoundException이 발생하는지 검증
        assertThatThrownBy(() -> productService.getProductById(999L))
                .isInstanceOf(CustomException.class)
                .satisfies(ex -> {
                    CustomException ce = (CustomException) ex;
                    assertThat(ce.getErrorCode()).isEqualTo(ErrorCode.PRODUCT_NOT_FOUND);
                });
    }

    @Test
    @DisplayName("상품 목록 전체 조회 성공")
    void getProductList_Success() {
        Product product2 = testProduct.toBuilder().id(2L).name("돼지 김치찌개").build();
        List<Product> expectedList = Arrays.asList(testProduct, product2);

        // Given: Repository가 findAll() 호출 시 목록 반환 설정
        given(productRepository.findAll()).willReturn(expectedList);

        // When: 서비스 메서드 호출
        List<Product> actualList = productService.getProductList();

        // Then: 반환된 목록의 크기와 내용 검증
        assertThat(actualList).isNotNull();
        assertThat(actualList).hasSize(2);
        assertThat(actualList).containsExactly(testProduct, product2);
        verify(productRepository).findAll();
    }

    @Test
    @DisplayName("새로운 상품 저장 성공")
    void saveProduct_Success() {
        // Given: ID가 없는 새 상품 (저장 전)
        Product newProduct = testProduct.toBuilder().id(null).name("새 메뉴").build();
        // Given: ID가 할당된 저장된 상품 (저장 후)
        Product savedProductWithId = newProduct.toBuilder().id(3L).build();

        // Given: Repository의 save 호출 시 ID가 할당된 객체 반환 설정
        given(productRepository.save(any(Product.class))).willReturn(savedProductWithId);

        // When: 서비스 메서드 호출
        Product savedProduct = productService.saveProduct(newProduct);

        // Then: 반환된 객체가 ID를 포함하는지 확인
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isEqualTo(3L);
        assertThat(savedProduct.getName()).isEqualTo("새 메뉴");
        verify(productRepository).save(newProduct);
    }

    @Test
    @DisplayName("상품 논리적 삭제 성공 (isDeleted=true, Description 변경)")
    void deleteProduct_Success() {
        // 1. Given: 조회 요청 시 상품이 존재한다고 가정
        given(productRepository.findById(1L)).willReturn(Optional.of(testProduct));

        // 2. Given: save 요청 시 변경된 객체를 반환한다고 가정
        // (실제 save는 testProduct 객체의 내부 값을 변경한 후 호출됨)
        given(productRepository.save(any(Product.class))).willAnswer(invocation -> invocation.getArgument(0));

        // When: 서비스 메서드 호출
        Product deletedProduct = productService.deleteProduct(1L);

        // Then: 엔티티의 상태 변경(논리적 삭제)이 예상대로 이루어졌는지 확인
        assertThat(deletedProduct.getIsDeleted()).isTrue();
        assertThat(deletedProduct.getDescription()).isEqualTo("상품 삭제");

        verify(productRepository).findById(1L);
        verify(productRepository).save(deletedProduct);
    }

    @Test
    @DisplayName("존재하지 않는 상품 삭제 시 ProductNotFoundException 발생")
    void deleteProduct_NotFound() {
        // Given: Repository가 ID 99로 조회 시 빈 Optional 반환 설정
        given(productRepository.findById(99L)).willReturn(Optional.empty());

        // When & Then: 메서드 호출 시 ProductNotFoundException이 발생하는지 검증
        assertThatThrownBy(() -> productService.deleteProduct(99L))
                .isInstanceOf(CustomException.class)
                .satisfies(ex -> {
                    CustomException ce = (CustomException) ex;
                    assertThat(ce.getErrorCode()).isEqualTo(ErrorCode.PRODUCT_NOT_FOUND);
                });
    }
}