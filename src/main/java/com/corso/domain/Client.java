package com.corso.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    private String name;
    private String telephone;

}
