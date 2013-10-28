<@content for="title">Books List</@content>

<div class="message"><@flash name="message"/></div>

<@link_to action="new_form">Add new book</@link_to>

<table>
    <tr>
        <td>Title</td>
        <td>Author</td>
        <td>Edit</td>
    </tr>
<#list books as book>
    <tr>
        <td>
            <@link_to action="show" id=book.id>${book.judul}</@link_to>
        </td>
        <td>
        ${book.deskripsi}</td>
        <td>
            <@confirm text="Are you sure you want to delete this book: " + book.judul + "?" form=book.id>
                Delete</@confirm>
            <@form  id=book.id action="delete" method="post" html_id=book.id />
        </td>
    </tr>
</#list>
</table>




