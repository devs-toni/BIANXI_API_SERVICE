package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorMother {

    public static List<Color> colors = new ArrayList<>(Arrays.asList(
            new Color(1L, "#12345"),
            new Color(2L, "#12346")
    ));
}
