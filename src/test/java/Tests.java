import org.junit.*;
import data.*;
import java.util.*;
import java.util.stream.*;

import static org.junit.Assert.*;

public class Tests {
    static String mkString(final String prefix, final Iterable<String> items, final String separator, final String suffix) {
        final StringBuilder sb = new StringBuilder(prefix);

        for (final String i : items) {
            sb.append(i).append(separator);
        }

        sb.setLength(sb.length() - separator.length());
        return sb.append(suffix).toString();
    }

    static final String SEP = ",";

    @Test
    public void test1() throws Exception {
        final Foo foo = new Foo();
        foo.name = "hello";

        final Extractor<Foo> ex = new FooNameExtractor();

        assertEquals(foo.name, ex.apply(foo));
    }

    @Test
    public void test2() throws Exception {
        final Foo foo1 = new Foo();
        foo1.name = "hello";
        final Foo foo2 = new Foo();
        foo2.name = "world";
        final List<Foo> foos = Arrays.asList(foo1, foo2);

        final Extractor<Foo> ex = new FooNameExtractor();

        assertEquals(Arrays.asList(foo1.name, foo2.name), foos.stream().map(ex::apply).collect(Collectors.toList()));
    }

    @Test
    public void test3() throws Exception {
        final Foo foo1 = new Foo();
        foo1.name = "hello";
        final Foo foo2 = new Foo();
        foo2.name = "world";
        final List<Foo> foos = Arrays.asList(foo1, foo2);

        final Extractor<Foo> ex1 = new FooNameExtractor();
        final Extractor<Foo> ex2 = new FooAgeExtractor();

        final List<Extractor<Foo>> exs = Arrays.asList(ex1, ex2);

        assertEquals(
            Arrays.asList(foo1.name + SEP + String.valueOf(foo1.age), foo2.name + SEP + String.valueOf(foo2.age)),
            foos.stream().map(foo -> mkString("", exs.stream().map(ex -> ex.apply(foo)).collect(Collectors.toList()), SEP, "")).collect(Collectors.toList())
        );
    }

    @Test
    public void test4() throws Exception {
        final Bar bar1 = new Bar();
        bar1.name = "a";
        final Bar bar2 = new Bar();
        bar2.name = "b";
        final Bar bar3 = new Bar();
        bar3.name = "c";
        final Bar bar4 = new Bar();
        bar4.name = "d";

        final Foo foo1 = new Foo();
        foo1.name = "hello";
        foo1.bars = Arrays.asList(bar1, bar3);
        final Foo foo2 = new Foo();
        foo2.name = "world";
        foo1.bars = Arrays.asList(bar2, bar4);

        bar1.foo = foo1;
        bar2.foo = foo2;
        bar3.foo = foo1;
        bar4.foo = foo2;

        final List<Bar> bars = Arrays.asList(bar1, bar2, bar3, bar4);

        final Extractor<Bar> ex1 = new BarNameExtractor();
        final Extractor<Bar> ex2 = new BarFooNameExtractor();
        final List<Extractor<Bar>> exs = Arrays.asList(ex1, ex2);

        final String bar1String = "a,hello";
        final String bar2String = "b,world";
        final String bar3String = "c,hello";
        final String bar4String = "d,world";

        assertEquals(
            Arrays.asList(bar1String, bar2String, bar3String, bar4String),
            bars.stream().map(bar -> mkString("", exs.stream().map(ex -> ex.apply(bar)).collect(Collectors.toList()), SEP, "")).collect(Collectors.toList())
        );
    }
}
