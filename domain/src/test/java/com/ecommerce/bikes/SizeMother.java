package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SizeMother {

    public static List<Size> sizes = new ArrayList<>(Arrays.asList(
            new Size(1L, "M", Collections.emptyList()),
            new Size(2L, "S", Collections.emptyList())
    ));
}
