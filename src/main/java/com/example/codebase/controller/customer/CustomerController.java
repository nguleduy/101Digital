package com.example.codebase.controller.customer;

import com.example.codebase.constant.Path;
import com.example.codebase.constant.UserType;
import com.example.codebase.controller.BaseController;
import com.example.codebase.dto.OrderDto;
import com.example.codebase.dto.QueueDto;
import com.example.codebase.dto.ShopDto;
import com.example.codebase.dto.UserDto;
import com.example.codebase.dto.request.SignInRequestDto;
import com.example.codebase.service.auth.AuthService;
import com.example.codebase.service.customer.CustomerService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Customer controller.
 */
@RequestMapping(Path.CUSTOMER)
public class CustomerController extends BaseController {

    private final CustomerService customerService;

    CustomerController(final CustomerService customerService,
                       final AuthService authService) {
        super(authService);
        this.customerService = customerService;
    }

    @Override
    @PostMapping(Path.SIGNUP)
    public ResponseEntity<UserDto> signup(UserDto request) {
        request.setType(UserType.CUSTOMER.name());
        return super.signup(request);
    }

    @Override
    @PostMapping(Path.LOGIN)
    public ResponseEntity<UserDto> login(SignInRequestDto request) {
        return super.login(request);
    }

    @GetMapping(Path.CUSTOMER_FIND_NEAREST_SHOPS)
    public ResponseEntity<List<ShopDto>> findNearestShops(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) {
        return ResponseEntity.ok(customerService.findNearestShops(latitude, longitude));
    }

    @PostMapping(Path.CUSTOMER_PLACE_ORDER)
    public ResponseEntity<QueueDto> placeOrder(@RequestBody OrderDto dto) {
        return ResponseEntity.ok(customerService.placeOrder(dto));
    }

    @GetMapping(Path.CUSTOMER_VIEW_QUEUE)
    public ResponseEntity<List<QueueDto>> viewQueueByShop(
            @PathVariable(value = "shopId") Long shopId) {
        return ResponseEntity.ok(customerService.viewQueueByShop(shopId));
    }

    @PostMapping(Path.CUSTOMER_CANCEL_ORDER)
    public ResponseEntity<QueueDto> cancelOrder(@RequestBody OrderDto dto) {
        return ResponseEntity.ok(customerService.cancelOrder(dto));
    }
}
