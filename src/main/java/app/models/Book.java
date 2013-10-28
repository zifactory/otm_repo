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

package app.models;

import app.cores.IModel;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import zi.helper.ZHelperModel;

@Table("tb_buku")
public class Book extends Model implements IModel {

    @Override
    public boolean insert() {
        setId(ZHelperModel.getGenerateID());
        return super.insert();
    }
}
