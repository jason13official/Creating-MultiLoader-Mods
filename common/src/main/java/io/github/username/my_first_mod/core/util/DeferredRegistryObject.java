package io.github.username.my_first_mod.core.util;

import java.util.function.Supplier;

/**
 * For clarity in our code, we create a custom supplier class (a.k.a. a lazy wrapper) for our registry objects. <br />
 * Your custom implementation can provide more functionality than what is present in this example. <br />
 *
 * <a href="https://github.com/jaredlll08/MultiLoader-Template/blob/1.18.2/examples/MultiLoader-Template-with-registration/Common/src/main/java/com/example/examplemod/registration/RegistryObject.java">Check out this example by jaredlll08 and contributors for an idea.</a>
 *
 * @param <T> the type of object being registered (i.e. Block, Item, CreativeModeTab, etc.)
 */
public interface DeferredRegistryObject<T> extends Supplier<T> {

    /**
     * This line is not required, and is only added for documentation purposes.
     *
     * @return The underlying object attached to this wrapper.
     */
    T get();
}
