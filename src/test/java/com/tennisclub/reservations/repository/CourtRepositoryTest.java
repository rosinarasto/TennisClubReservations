package com.tennisclub.reservations.repository;

import com.tennisclub.reservations.model.factory.CourtFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CourtRepositoryTest {

    @Autowired
    private CourtRepository courtRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void findByNumber_returnsEmpty() {
        var actual = courtRepository.findByCourtNumber(5);
        assertThat(actual).isEmpty();
    }

    @Test
    public void findByNumber_returnsCourt() {
        var court = CourtFactory.createCourt(5);
        em.persist(court.getSurface());
        em.persist(court);

        var actual = courtRepository.findByCourtNumber(5);

        assertThat(actual).contains(court);
    }
}
