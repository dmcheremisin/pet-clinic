package info.cheremisin.petclinic.service.map;

import info.cheremisin.petclinic.model.BaseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long>{

    protected Map<Long, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet(map.values());
    }

    public T findById(ID id) {
        return map.get(id);
    }

    public T save(T object) {
        Objects.requireNonNull(object, "Object id cannot be null");

        if (object.getId() == null) {
            object.setId(getNextId());
        }
        map.put(object.getId(), object);

        return object;
    }

    public void deleteById(ID id) {
        map.remove(id);
    }

    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId() {
        return map.isEmpty() ? 1 : Collections.max(map.keySet()) + 1;
    }

}
