import data.*;
import java.util.*;

public class BarFooNameExtractor extends AbstractExtractor<Bar> {
    public final Map<Foo, String> cache = new HashMap<>(); // Guava cache?
    public static final FooNameExtractor fooEx = new FooNameExtractor();

    public BarFooNameExtractor() {
        super("BarFooNameExtractor");
    }

    @Override
    public String apply(final Bar bar) {
        return cache.computeIfAbsent(bar.foo, fooEx::apply);
    }
}
