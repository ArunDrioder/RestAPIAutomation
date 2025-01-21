package POJO;

import java.util.List;

public class Orders
{
    public List<OrderDetails> orderDetailsList;

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }
}
