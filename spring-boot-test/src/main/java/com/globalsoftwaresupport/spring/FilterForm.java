package com.globalsoftwaresupport.spring;

import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Mantiene un valor de un tipo de filtro seleccionado para un nombre dado. El valor se devolverá como un {@link Optional}
 * para evitar los NPE obligando al llamador a realizar la verificación.
 * Corresponde al filtro que se aplicará cuando se interactúe con un componente del formulario de búsqueda. Cada filtro
 * tendrá un componente asociado, y cada componente 1 o varios filtros.
 */
public class FilterForm {

    /**
     * Nombre del filtro. No debería haber dos nombres iguales en el objeto {@link SelectedFiltersForm}
     */
    private final String name;
    /**
     * Valor tipado del filtro
     */
    private final Object value;

    private <T> FilterForm(@NonNull final String name, @NonNull final T value) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(value, "value must not be null");

        this.name = name;
        this.value = value;
    }

    // for empty filters
    private FilterForm(@NonNull final String name) {
        Objects.requireNonNull(name, "name must not be null");

        this.name = name;
        this.value = null;
    }

    /**
     * Creación de un filtro que no tiene un valor seleccionado.
     *
     * @param name nombre del filtro
     * @return FilterForm
     */
    public static FilterForm empty(@NonNull final String name) {
        return new FilterForm(name);
    }

    /**
     * Creación de un filtro con un nombre y un valor tipado.
     *
     * @param name  nombre del filtro
     * @param value valor del filtro
     * @param <T>   tipo del valor del filtro
     * @return FilterForm
     */
    public static <T> FilterForm of(@NonNull final String name, @NonNull final T value) {
        return new FilterForm(name, value);
    }

    /**
     * Creación de un filtro con un nombre y un valor tipado que puede ser nulo. Si es nulo construirá un filtro vacío.
     *
     * @param name  nombre del filtro
     * @param value valor del filtro
     * @param <T>   tipo del valor del filtro
     * @return FilterForm
     */
    public static <T> FilterForm ofNullable(@NonNull final String name, final T value) {
        if (value == null) {
            return FilterForm.empty(name);
        }

        return new FilterForm(name, value);
    }

    /**
     * Devuelve el nombre del filtro
     *
     * @return nombre del filtro
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve si el filtro está vacío.
     *
     * @return true si está vacío; false en caso contrario
     */
    public boolean isEmpty() {
        return value == null;
    }

    /**
     * Obtiene el valor del filtro mediante un Optinal.
     *
     * @param <T> tipo del valor
     * @return optional con el valor del filtro
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getValue() {
        if (value == null) {
            return Optional.empty();
        }

        return Optional.of((T) value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterForm that = (FilterForm) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return value == null ? null : value.toString();
    }
}
