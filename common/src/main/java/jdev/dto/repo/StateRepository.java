package jdev.dto.repo;

import jdev.dto.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {
    public ArrayList<State> findStatesByAutoIdIs(String autoId);
}
