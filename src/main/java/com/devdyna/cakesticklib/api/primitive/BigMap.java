package com.devdyna.cakesticklib.api.primitive;

import java.util.HashMap;
import java.util.function.BiConsumer;

import com.devdyna.cakesticklib.api.utils.ArrayUtils;

public class BigMap<K, V> {

    private HashMap<K, V> map = new HashMap<>();

    private BigMap(Object... p) {

        if (p.length % 2 != 0)
            p = ArrayUtils.concat(p, null);

        for (var i = 0; i < p.length; i += 2)
            map.put((K) p[i], (V) p[i + 1]);

    }

    public static <K, V> BigMap<K, V> of(Object... p) {
        return new BigMap<>(p);
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        map.forEach(action);
    }
}
