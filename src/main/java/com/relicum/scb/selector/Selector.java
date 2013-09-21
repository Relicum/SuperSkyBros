package com.relicum.scb.selector;

import com.relicum.scb.SCB;
import org.bukkit.Material;


/**
 * SuperSkyBros Used to Arena Selections First Created 11/09/13
 *
 * @author Relicum
 * @version 0.1
 */
public class Selector implements AutoCloseable {

    public String item;


    public Selector() {
        loadItem();
    }


    private void loadItem() {

        item = SCB.getInstance().getConfig().getString("selectorId");

    }


    public Material getItem() {

        return Material.valueOf(this.item);


    }

    //Used to run the Closeable Object
/*    try(Selector sel = new Selector()) {
        n = sel.getItem().name();
        System.out.println("The material was " + n);
        System.out.println("The material was " + n);

    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println("The material was " + n);*/


    /**
     * Closes this resource, relinquishing any underlying resources. This method is invoked automatically on objects
     * managed by the {@code try}-with-resources statement.
     * <p/>
     * <p>While this interface method is declared to throw {@code Exception}, implementers are <em>strongly</em>
     * encouraged to declare concrete implementations of the {@code close} method to throw more specific exceptions, or
     * to throw no exception at all if the close operation cannot fail.
     * <p/>
     * <p><em>Implementers of this interface are also strongly advised to not have the {@code close} method throw {@link
     * InterruptedException}.</em>
     * <p/>
     * This exception interacts with a thread's interrupted status, and runtime misbehavior is likely to occur if an
     * {@code InterruptedException} is {@linkplain Throwable#addSuppressed suppressed}.
     * <p/>
     * More generally, if it would cause problems for an exception to be suppressed, the {@code AutoCloseable.close}
     * method should not throw it.
     * <p/>
     * <p>Note that unlike the {@link java.io.Closeable#close close} method of {@link java.io.Closeable}, this {@code
     * close} method is <em>not</em> required to be idempotent.  In other words, calling this {@code close} method more
     * than once may have some visible side effect, unlike {@code Closeable.close} which is required to have no effect
     * if called more than once.
     * <p/>
     * However, implementers of this interface are strongly encouraged to make their {@code close} methods idempotent.
     *
     * @throws Exception if this resource cannot be closed
     */
    @Override
    public void close() throws Exception {
        System.out.println("Closing!");
    }
}
