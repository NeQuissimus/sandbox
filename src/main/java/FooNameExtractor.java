import data.Foo;
import java.util.concurrent.TimeUnit;

class FooNameExtractor extends AbstractExtractor<Foo> {
    public FooNameExtractor() {
        super("FooNameExtractor");
    }

    @Override
    public String apply(final Foo foo) {
        System.out.println("Get name, slow!");
        try { TimeUnit.SECONDS.sleep(2L); } catch (final Exception e) {}
        return foo.name;
    }
}
