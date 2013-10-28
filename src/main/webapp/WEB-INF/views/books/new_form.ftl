<@content for="title">Add new book</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adding new book</h2>


<@form action="create" method="post">
    <table style="margin:30px">
        <tr>
            <td>Book deskripsi</td>
            <td><input type="text" name="deskripsi" value="${(flasher.params.deskripsi)!}"> *
                            <span class="error">${(flasher.errors.deskripsi)!}</span>
            </td>
        </tr>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="judul" value="${(flasher.params.judul)!}"> *
                            <span class="error">${(flasher.errors.judul)!}</span>
            </td>
        </tr>
        <tr>
            <td>ISBN:</td>
            <td><input type="text" name="isbn" value="${(flasher.params.isbn)!}"> *
                <span class="error">${(flasher.errors.isbn)!}</span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><@link_to>Cancel</@link_to> | <input type="submit" value="Add new book"></td>

        </tr>
    </table>
</@form>



