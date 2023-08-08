package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.entity.SizeEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SizeMother {

    public static List<SizeEntity> sizesDAO = new ArrayList<>(Arrays.asList(
            new SizeEntity(1L, "M"),
            new SizeEntity(2L, "S")
    ));

    public static List<Size> sizes = new ArrayList<>(Arrays.asList(
            new Size(1L, "M", Collections.emptyList()),
            new Size(2L, "S", Collections.emptyList())
    ));
}
