package cn.threeant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthHeader {

    private Long userId;
    private String accountName;
    private Role role;

}
