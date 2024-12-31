package com.tennisclub.reservations.repository;

import com.tennisclub.reservations.model.Surface;
import com.tennisclub.reservations.model.factory.SurfaceFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
public class SurfaceRepositoryTest {

    @Autowired
    private SurfaceRepository surfaceRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void findByName_returnsEmpty() {
        var actual = surfaceRepository.findByName("grass");
        assertThat(actual).isEmpty();
    }

    @Test
    public void findByName_returnsSurface() {
        var surface = SurfaceFactory.createSurface("grass");
        em.persist(surface);

        var actual = surfaceRepository.findByName("grass");

        assertThat(actual).contains(surface);
    }
}
