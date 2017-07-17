package lsa.sample.service;

import lsa.sample.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

/*
    @Query("select r.rating as rating, count(r) as count "
            + "from Review r where r.hotel = ?1 group by r.rating order by r.rating DESC")
    List<RatingCount> findRatingCounts(Hotel hotel);
    */
}
