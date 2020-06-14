//package com.icommerce.account.controller;
//
//import com.icommerce.account.IcAccountApplication;
//import com.icommerce.account.entity.Account;
//import com.icommerce.account.entity.Role;
//import com.icommerce.account.model.request.LoginRequest;
//import com.icommerce.account.model.response.JwtAuthenticationToken;
//import com.icommerce.account.repository.AccountRepository;
//import com.icommerce.core.constant.Auth;
//import com.icommerce.core.constant.AuthRole;
//import com.icommerce.core.utils.EncrytedPasswordUtils;
//import com.icommerce.core.utils.JsonUtils;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = IcAccountApplication.class)
//public class AuthControllerTest {
//
//    private MockMvc mvc;
//
//    @MockBean
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Before
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
////                .apply(SecurityMockMvcConfigurers.springSecurity())
//                .build();
//    }
//
//    @Test
//    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
//            throws Exception {
//
//        LoginRequest request = new LoginRequest();
//        request.setUserName("ptgiang56it@gmail.com");
//        request.setPassword("12345678");
//
//        Account account = createAccount();
//        Mockito.when(accountRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.ofNullable(account));
//        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(account));
//
//        mvc.perform(post("/api/v1/auth")
//                .content(JsonUtils.toJson(request))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("accessToken", is(notNullValue())))
//                .andExpect(jsonPath("refreshToken", is(notNullValue())))
//                .andExpect(jsonPath("tokenType", is(Auth.BEARER_PREFIX)))
//        ;
//    }
//
//    private JwtAuthenticationToken mockAuthTokenResponse () {
//        JwtAuthenticationToken response = new JwtAuthenticationToken();
//        response.setAccessToken("eyJhbGciOiJIUzUxMiJ9." +
//                "eyJzdWIiOiJ7XCJpZFwiOjEsXCJ1c2VybmFtZVwiOlwicHRnaWFuZzU2aXRAZ21haWwuY29tXCIsXCJyb2xlQ" +
//                "29kZXNcIjpbXCJBRE1JTlwiXX0iLCJzY29wZXMiOlsiUk9MRV9BRE1JTiJdLCJpc3MiOiJodHRwczovL2ljb21tZXJ" +
//                "jZS5jb20vIiwiaWF0IjoxNTkyMDM0MTc2LCJleHAiOjI0NTYwMzQxNzZ9." +
//                "5HZuRPDMJ0usHSO3Tdhrlg6ConYvrC_llFmE8mzddP4h_bH68RL41AMIGVNhy1_kk_ug5hZoloxJU_j7KuN4qQ");
//        response.setRefreshToken("eyJhbGciOiJIUzUxMiJ9." +
//                "eyJzdWIiOiIxIiwic2NvcGVzIjpbIlJPTEVfUkVGUkVTSF9UT0tFTiJdLCJpc3MiOiJodHRwczovL2ljb21tZXJjZS5j" +
//                "b20vIiwiaWF0IjoxNTkyMDM0MTc2LCJleHAiOjM3ODgwMDM0MTc2fQ." +
//                "2c6ojjzR-Wulsj0pKSsj22ji5NR5CCB_3pbJ66kJixQa6UCpCNSmlHUR0D5P03Xb3N6iQn1oEq7qqdMjYm-rdA");
//        response.setTokenType(Auth.BEARER_PREFIX);
//        return response;
//    }
//
//    private Account createAccount() {
//        Account account = new Account();
//        account.setId(1L);
//        account.setUsername("ptgiang56it@gmail.com");
//        account.setPassword(EncrytedPasswordUtils.encrytePassword("12345678"));
//        account.setRoles(createRoles());
//        return account;
//    }
//
//    private Set<Role> createRoles() {
//        Set<Role> roles = new HashSet<>();
//        roles.add(createRole());
//        return roles;
//    }
//
//    private Role createRole() {
//        Role role = new Role();
//        role.setId(1L);
//        role.setCode(AuthRole.CUSTOMER.name());
//        role.setName("Customer");
//        return role;
//    }
//}
