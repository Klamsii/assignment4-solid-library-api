package repository.impl;

import dto.BookWithAuthor;
import model.BookBase;
import model.EBook;
import repository.interfaces.BookRepositoryInterface;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryJdbc implements BookRepositoryInterface {
    public List<BookWithAuthor> findAllWithAuthors() {
        List<BookWithAuthor> result = new ArrayList<>();

        String sql = """
        SELECT b.id, b.name, b.format, a.name AS author
        FROM books b
        JOIN authors a ON b.author_id = a.id
        ORDER BY b.id
    """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(new BookWithAuthor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("format"),
                        rs.getString("author")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("DB error: find all books with authors");
        }

        return result;
    }


    @Override
    public void create(BookBase book) {
        String sql = "INSERT INTO books (name, format, author_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getName());
            ps.setString(2, book.getFormat());
            ps.setInt(3, book.getAuthor().getId());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("DB error: create book");
        }
    }

    @Override
    public Optional<BookBase> findById(int id) {
        String sql = "SELECT name, format FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new EBook(id, rs.getString("name"), 1.0)
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("DB error: find book");
        }

        return Optional.empty();
    }

    @Override
    public List<BookBase> findAll() {
        List<BookBase> books = new ArrayList<>();
        String sql = "SELECT id, name, format FROM books";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                books.add(
                        new EBook(
                                rs.getInt("id"),
                                rs.getString("name"),
                                1.0
                        )
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("DB error: find all books");
        }

        return books;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("DB error: delete book");
        }
    }

    @Override
    public Optional<BookWithAuthor> findByIdWithAuthor(int id) {
        String sql = """
            SELECT b.id, b.name, b.format, a.name AS author
            FROM books b
            JOIN authors a ON b.author_id = a.id
            WHERE b.id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(new BookWithAuthor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("format"),
                        rs.getString("author")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("DB error: find book with author");
        }

        return Optional.empty();
    }
}
