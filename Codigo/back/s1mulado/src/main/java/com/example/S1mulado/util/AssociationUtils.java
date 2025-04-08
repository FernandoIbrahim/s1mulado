package com.example.S1mulado.util;

import com.example.S1mulado.util.interfaces.Relatable;

import java.util.List;

public class AssociationUtils {

    /**
     * Configures the relationship between a parent entity and a list of child entities.
     * This method ensures that each child entity in the given list has its parent reference set correctly.
     *
     * <p>This is particularly useful in bidirectional relationships, where the child entities need
     * to be aware of their parent before persisting the parent entity in the database.</p>
     *
     * @param <T> The type of the child entities, which must implement {@link Relatable}.
     * @param <U> The type of the parent entity.
     * @param parent The parent entity to associate with the child entities.
     * @param children The list of child entities to be associated with the parent. If null, no action is taken.
     */

    public static <T extends Relatable<U>, U> void associateParent(U parent, List<T> children) {

        if (children != null) {
            for (T child : children) {
                child.setParent(parent);
            }
        }

    }

}
