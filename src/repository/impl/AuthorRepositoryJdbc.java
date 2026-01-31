package repository.impl;
import java.util.List;
import java.util.ArrayList;
import model.Author;
import repository.interfaces.AuthorRepositoryInterface;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.Optional;

public class AuthorRepositoryJdbc implements AuthorRepositoryInterface {
    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT id, name FROM authors";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                authors.add(
                        new Author(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("DB error: find all authors");
        }

        return authors;
    }


    @Override
    public void create(Author author) {
        String sql = "INSERT INTO authors (name) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, author.getName());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("DB error: create author");
        }
    }

    @Override
    public Optional<Author> findById(int id) {
        String sql = "SELECT id, name FROM authors WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new Author(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("DB error: find author");
        }

        return Optional.empty();
    }

    @Override
    public Optional<Integer> findIdByName(String name) {
        String sql = "SELECT id FROM authors WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt("id"));
            }

        } catch (Exception e) {
            throw new RuntimeException("DB error: find author id by name");
        }

        return Optional.empty();
    }

    @Override
    public Optional<Author> findFirstAuthorWithMultipleBooks() {
        String sql = """
            SELECT a.id, a.name
            FROM authors a
            JOIN books b ON a.id = b.author_id
            GROUP BY a.id, a.name
            HAVING COUNT(b.id) > 1
            ORDER BY a.id
            LIMIT 1
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return Optional.of(
                        new Author(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("DB error: find author with multiple books");
        }

        return Optional.empty();
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM authors WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("DB error: delete author");
        }
    }
}
