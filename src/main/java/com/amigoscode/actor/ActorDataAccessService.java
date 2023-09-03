package com.amigoscode.actor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ActorDataAccessService implements ActorDao {
    private final JdbcTemplate jdbcTemplate;

    public ActorDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Actor> selectAllActors() {
        var sql = """
                SELECT id ,name
                FROM jdbc.public.actor
                LIMIT 100
                """;
        return jdbcTemplate.query(sql, new ActorRowMapper());

    }

    @Override
    public Optional<Actor> getActorById(Integer id) {
        var sql = """
                SELECT id,name
                FROM jdbc.public.actor
                WHERE id=?
                """;
        return jdbcTemplate.query(sql, new ActorRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public Integer insertActor(Actor actor) {
        var sql = """
                INSERT INTO jdbc.public.actor(name)
                VALUES (?)
                """;
        return jdbcTemplate.update(
                sql,
                actor.name());
    }

    @Override
    public Integer deleteActorById(Integer id) {
        var sql = """
                DELETE FROM jdbc.public.actor
                WHERE id=?
                """;
        return jdbcTemplate.update(sql, id);
    }


    @Override
    public void deleteAllActors() {
        var sql = """
                DELETE FROM jdbc.public.actor
                """;
        jdbcTemplate.update(sql);
    }

    @Override
    public int updateActor(Integer id, Actor actor) {
        var sql = """
                UPDATE jdbc.public.actor
                SET name = ?
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, actor.name(), id);
    }
}
