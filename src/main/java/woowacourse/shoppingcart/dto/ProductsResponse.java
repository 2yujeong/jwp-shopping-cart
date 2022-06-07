package woowacourse.shoppingcart.dto;

import java.util.List;

public class ProductsResponse {

    private List<ProductResponse> productResponses;

    public ProductsResponse() {
    }

    public ProductsResponse(List<ProductResponse> productResponses) {
        this.productResponses = productResponses;
    }

    public List<ProductResponse> getProductResponses() {
        return productResponses;
    }
}
