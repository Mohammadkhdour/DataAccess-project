package com.khdour;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


import java.util.List;


public interface SQLDao {

      @SqlUpdate("INSERT INTO book (isbn, title, language, author, description) VALUES (:isbn, :title, :language, :author, :description)")
    void insert(@BindBean Book book);

      @SqlQuery("SELECT title FROM <table> ")
    public List<String> getTitles(@Define("table") String book);

      @SqlUpdate("Delete From <table> where isbn = :isbn")
    void deleteBook(@Define("table") String table, @Bind("isbn") int isbn);

      @SqlUpdate("Delete From <table> where isbn > 0")
    int deleteAll(@Define("table") String table);

      @SqlUpdate("Update book set title = :title  where isbn = :isbn")
      int updateTitle( @Bind("isbn") int isbn, @Bind("title") String title);
}
