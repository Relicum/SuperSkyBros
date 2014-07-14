package org.jetbrains.annotations;


import java.lang.annotation.*;

/**
 * Specifies that a method parameter accepts arguments which must be valid property
 * keys in a specific resource bundle. When a string literal which is not a property
 * key in the specified bundle is passed as a parameter, IntelliJ IDEA highlights
 * it as an error. The annotation is also used to provide completion in string literals
 * passed as parameters.
 *
 * @author max
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.FIELD})
public @interface PropertyKey {

    /**
     * The full-qualified name of the resource bundle in which the property keys must
     * be present. Consists of a full-qualified name of the package corresponding to the
     * directory where the resource bundle is located and the base name of the resource
     * bundle (with no locale specifier or extension), separated with a dot.
     */
    String resourceBundle();
}
