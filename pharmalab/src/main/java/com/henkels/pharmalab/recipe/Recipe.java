package com.henkels.pharmalab.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Recipe {
    private Integer id;
    private String title;
}
