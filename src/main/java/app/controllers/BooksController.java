/*
Copyright 2009-2010 Igor Polevoy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package app.controllers;

import app.models.Book;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.POST;
import zi.helper.ZHelperModel;

public class BooksController extends AppController {

    public void index() {
        view("books", Book.findAll());
    }

    public void show() {
        //this is to protect from URL hacking
        Book b = Book.findById(getId());
        if (b != null) {
            view("book", b);
        } else {
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @POST
    public void create() {
        Book book = new Book();
        book.fromMap(params1st());
        //book.setId(ZHelperModel.getGenerateID());
//        book.set("isbn", param("isbn"));
//        book.set("deskripsi", param("deskripsi"));
//        book.set("judul", param("judul"));

        //System.out.println(book.save());

        if (!book.insert()) {
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", book.errors());
            flash("params", params1st());
            System.out.println(params1st());
            redirect(BooksController.class, "new_form");
        } else {
            flash("message", "New book was added: " + book.get("judul"));
            redirect(BooksController.class);
        }

        System.out.println(book.errors());
    }

    public void delete() {

        Book b = Book.findById(getId());
        String title = b.getString("judul");
        b.delete();
        flash("message", "Book: '" + title + "' was deleted");
        redirect(BooksController.class);
    }

    public void newForm() {
    }
}
