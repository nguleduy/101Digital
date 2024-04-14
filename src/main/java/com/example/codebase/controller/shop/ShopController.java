package com.example.codebase.controller.shop;

import com.example.codebase.constant.Path;
import com.example.codebase.constant.UserType;
import com.example.codebase.controller.BaseController;
import com.example.codebase.dto.MenuDto;
import com.example.codebase.dto.QueueDto;
import com.example.codebase.dto.ShopDto;
import com.example.codebase.dto.UserDto;
import com.example.codebase.dto.request.SignInRequestDto;
import com.example.codebase.service.MenuService;
import com.example.codebase.service.QueueService;
import com.example.codebase.service.ShopService;
import com.example.codebase.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Shop controller.
 */
@RequestMapping(Path.SHOP)
public class ShopController extends BaseController {
    private final ShopService shopService;
    private final MenuService menuService;
    private final QueueService queueService;

    /**
     * Constructor.
     */
    public ShopController(final AuthService authService,
                          final ShopService shopService,
                          final MenuService menuService,
                          final QueueService queueService) {
        super(authService);
        this.shopService = shopService;
        this.menuService = menuService;
        this.queueService = queueService;
    }

    @Override
    @PostMapping(Path.SIGNUP)
    public ResponseEntity<UserDto> signup(UserDto request) {
        request.setType(UserType.SHOP_OPERATOR.name());
        return super.signup(request);
    }

    @Override
    @PostMapping(Path.LOGIN)
    public ResponseEntity<UserDto> login(SignInRequestDto request) {
        return super.login(request);
    }

    // Setup/configure/manage opening/closing times for shop
    @PostMapping(Path.SHOP_OWNER_SETUP_SHOP)
    public ResponseEntity<ShopDto> setupShop(@RequestBody ShopDto dto) {
        return ResponseEntity.ok(shopService.setupShop(dto));
    }

    // Manage menu
    @PostMapping(Path.SHOP_OWNER_MANAGE_MENU)
    public ResponseEntity<MenuDto> manageMenu(@RequestBody MenuDto request) {
        return ResponseEntity.ok(menuService.manageMenu(request));
    }

    // Configure queue
    @PostMapping(Path.SHOP_OWNER_CONFIGURE_QUEUE)
    public ResponseEntity<QueueDto> configureQueue(@RequestBody QueueDto dto) {
        return ResponseEntity.ok(queueService.configureQueue(dto));
    }
}
