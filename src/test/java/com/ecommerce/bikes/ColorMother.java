package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.entity.ColorEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorMother {

    public static List<ColorEntity> colorsDAO = new ArrayList<>(Arrays.asList(
            new ColorEntity(1L, "#12345"),
            new ColorEntity(2L, "#12346")
    ));

    public static List<Color> colors = new ArrayList<>(Arrays.asList(
            new Color(1L, "#12345"),
            new Color(2L, "#12346")
    ));
}
