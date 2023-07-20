package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.entity.ColorDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorMother {

    public static List<ColorDAO> colorsDAO = new ArrayList<>(Arrays.asList(
            new ColorDAO(1L, "#12345"),
            new ColorDAO(2L, "#12346")
    ));

    public static List<Color> colors = new ArrayList<>(Arrays.asList(
            new Color(1L, "#12345"),
            new Color(2L, "#12346")
    ));
}
