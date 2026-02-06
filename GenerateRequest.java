package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateRequest {

    public GenerateRequest(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}
	private String name;
    private String regNo;
    private String email;
}
