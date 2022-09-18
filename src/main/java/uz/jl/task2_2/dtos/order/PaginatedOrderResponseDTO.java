package uz.jl.task2_2.dtos.order;


import lombok.Builder;
import lombok.Data;
import uz.jl.task2_2.domains.OrderTable;

import java.util.List;

@Data
@Builder
public class PaginatedOrderResponseDTO {

    private List<OrderTable> orderTableList;
    private Long numberOfItems;
    private int numberOfPages;

}


//@Data
//@Builder
//public class PaginatedBookResponse {
//    private List<Book> bookList;
//    private Long numberOfItems;
//    private int numberOfPages;
//}