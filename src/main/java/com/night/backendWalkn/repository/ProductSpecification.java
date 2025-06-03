package com.night.backendWalkn.repository;

import com.night.backendWalkn.model.entities.Product;
import com.night.backendWalkn.model.enums.Season;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> hasBrand(String brand) {
        return (root, query, cb) -> brand == null ? null : cb.equal(root.get("brand"), brand);
    }

    public static Specification<Product> hasModel(String model) {
        return (root, query, cb) -> model == null ? null : cb.equal(root.get("model"), model);
    }

    public static Specification<Product> hasColor(String color) {
        return (root, query, cb) -> color == null ? null : cb.equal(root.get("color"), color);
    }

    public static Specification<Product> hasSeason(Season season) {
        return (root, query, cb) -> season == null ? null : cb.equal(root.get("season"), season);
    }

    public static Specification<Product> costBetween(Integer min, Integer max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min == null) return cb.le(root.get("cost"), max);
            if (max == null) return cb.ge(root.get("cost"), min);
            return cb.between(root.get("cost"), min, max);
        };
    }
}
