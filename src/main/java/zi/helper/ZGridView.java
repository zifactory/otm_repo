/**
 * Copyright 2013 Nanang Suryadi || nanang.ask@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Namespace zi.helper
 * Class ZGridView.java
 * @date 12/17/13
 * @author Nanang Suryadi <nanang.ask@gmail.com>
 */
package zi.helper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activeweb.AppController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ZGridView<T extends Model> {
    private Class<? extends AppController> c;
    private String[] aColumns;
    private HashMap<String, Object> grid;
    private Map<String, String> params;

    /*
    *  aColumns == field database
    *  Columns == field table
    *  Model == Bean Table database
    *  Params == HTTP Respond
    *  Parent == AppController<this>
    *  Grid == template ftl
    * */
    public ZGridView() {
        this.grid = new HashMap<String, Object>();
    }

    public ZGridView(Class<? extends AppController> controller) {
        this.c = controller;
        this.grid = new HashMap<String, Object>();
    }

    public <V extends Model> String JsonDatatable( V V, Map<String,String> params) {
//        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/otm_repo", "root", "root");
  /*
        *  column field from database
        * */
//        String[] aColumns = {"id", "keterangan", "root"};
        /*
        *
        * * Paging
        $sLimit = "";
        if ( isset( $_GET['iDisplayStart'] ) && $_GET['iDisplayLength'] != '-1' )
        {
            $sLimit = "LIMIT ".intval( $_GET['iDisplayStart'] ).", ".
                intval( $_GET['iDisplayLength'] );
        }
        * */
        //Paging
        int sLimit = 0;
        int sStart = 0;
        if (params.containsKey("iDisplayStart") && !params.get("iDisplayLength").equals("-1")) {
            sLimit = Integer.parseInt(params.get("iDisplayLength"));
            sStart = Integer.parseInt(params.get("iDisplayStart"));
        }
        /*
        *
	    * Ordering
        $sOrder = "";
        if ( isset( $_GET['iSortCol_0'] ) )
        {
            $sOrder = "ORDER BY  ";
            for ( $i=0 ; $i<intval( $_GET['iSortingCols'] ) ; $i++ )
            {
                if ( $_GET[ 'bSortable_'.intval($_GET['iSortCol_'.$i]) ] == "true" )
                {
                    $sOrder .= "`".$aColumns[ intval( $_GET['iSortCol_'.$i] ) ]."` ".
                    ($_GET['sSortDir_'.$i]==='asc' ? 'asc' : 'desc') .", ";
                }
            }

            $sOrder = substr_replace( $sOrder, "", -2 );
            if ( $sOrder == "ORDER BY" )
            {
                $sOrder = "";
            }
        }
        * */

        String sOrder = "";
        if (params.containsKey("iSortCol_0")) {
            for (int i = 0; i < Integer.parseInt(params.get("iSortingCols")); i++) {

                if (params.get("bSortable_" + params.get("iSortCol_" + i)).equalsIgnoreCase("true")) {
                    sOrder += "`" + aColumns[Integer.parseInt(params.get("iSortCol_" + i))] + "` " +
                            (params.get("sSortDir_" + i).equalsIgnoreCase("asc") ? "asc" : "desc");
                }
            }
        }
    /*
     *
     *
	 * Filtering
	 * NOTE this does not match the built-in DataTables filtering which does it
	 * word by word on any field. It's possible to do here, but concerned about efficiency
	 * on very large tables, and MySQL's regex functionality is very limited
	 *
        $sWhere = "";
        if ( isset($_GET['sSearch']) && $_GET['sSearch'] != "" )
        {
            $sWhere = "WHERE (";
            for ( $i=0 ; $i<count($aColumns) ; $i++ )
            {
                if ( isset($_GET['bSearchable_'.$i]) && $_GET['bSearchable_'.$i] == "true" )
                {
                    $sWhere .= $aColumns[$i]." LIKE '%".mysql_real_escape_string( $_GET['sSearch'] )."%' OR ";
                }
            }
            $sWhere = substr_replace( $sWhere, "", -3 );
            $sWhere .= ')';
        }
     * */
        String sWhere = "";
        Vector<Object> sParams = new Vector<Object>();

        if (params.containsKey("sSearch") && !params.get("sSearch").equals("")) {
            for (int i = 0; i < aColumns.length; i++) {
                if (params.containsKey("bSearchable_" + i) && params.get("bSearchable_" + i).equalsIgnoreCase("true")) {
                    if (i == aColumns.length - 1) {
                        sWhere += aColumns[i] + " LIKE ? ";
                    } else sWhere += aColumns[i] + " LIKE ?  OR ";
                    String str = params.get("sSearch").equals("") ? "" : params.get("sSearch");
                    sParams.add("%" + str + "%");
                }
            }
        }

    /*    *//* Individual column filtering *//*
        for (int i = 0; i < aColumns.length; i++) {
            if (params().containsKey("bSearchable_" + i) && param("bSearchable_" + i).equals("true") && !param("sSearch_" + i).equals("")) {
                if (!sWhere.equals("")) sWhere += " AND ";

                sWhere += aColumns[i] + " LIKE ? ";
                sParams.add("%" + param("sSearch_" + i) + "%");
            }
        }*/
/*
     * SQL queries
	 * Get data to display

        $sQuery = "
        SELECT SQL_CALC_FOUND_ROWS ".str_replace(" , ", " ", implode(", ", $aColumns))."
        FROM   $sTable
        $sWhere
                $sOrder
        $sLimit
        ";
        $rResult = mysql_query( $sQuery, $gaSql['link'] ) or fatal_error( 'MySQL Error: ' . mysql_errno() );
*/

        LazyList<V> rResult = V.where(sWhere, sParams.toArray())
                .offset(sStart)
                .limit(sLimit)
                .orderBy(sOrder);
        /* Total data set length
        $sQuery = "
        SELECT COUNT(".$sIndexColumn.")
        FROM   $sTable
        ";
        $rResultTotal = mysql_query( $sQuery, $gaSql['link'] ) or fatal_error( 'MySQL Error: ' . mysql_errno() );
        $aResultTotal = mysql_fetch_array($rResultTotal);
        $iTotal = $aResultTotal[0];
* */
        Long iTotal = V.count();
    /* Data set length after filtering
        $sQuery = "
        SELECT FOUND_ROWS()
        ";
        $rResultFilterTotal = mysql_query( $sQuery, $gaSql['link'] ) or fatal_error( 'MySQL Error: ' . mysql_errno() );
        $aResultFilterTotal = mysql_fetch_array($rResultFilterTotal);
        $iFilteredTotal = $aResultFilterTotal[0];
*/
        int iFilteredTotal = params.containsKey("sSearch") && !params.get("sSearch").equals("") ? rResult.size() : iTotal.intValue();

        /*
        * Data aaData Vector
        * */
        Vector<Object> aaData = new Vector<Object>();
        for (V cat : rResult) {
            Vector<Object> ct = new Vector<Object>();
            ct.removeAllElements();
            for (String sColumn : aColumns) {
                ct.addElement(cat.get(sColumn));
            }
            aaData.addElement(ct);
        }
         /*
         * Output

        $output = array(
                "sEcho" => intval($_GET['sEcho']),
                "iTotalRecords" => $iTotal,
                "iTotalDisplayRecords" => $iFilteredTotal,
                "aaData" => array()
        );
         */

        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("sEcho", params.get("sEcho"));
        output.put("iTotalRecords", iTotal);
        output.put("iTotalDisplayRecords", iFilteredTotal);
        output.put("aaData", aaData.toArray());
        ObjectMapper mapper = new ObjectMapper();

        String hasil = "";
        try {
            hasil = params.get("callback") + "(" + mapper.writeValueAsString(output) + ");";
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Base.close();
        return hasil;
    }

    public void renderGrid() {

        if (aColumns.length < -1) throw new NegativeArraySizeException();
        try {
            Method view = c.getDeclaredMethod("view", HashMap.class);
            view.invoke("gridview", grid);
            Method render = c.getDeclaredMethod("render", String.class);
            render.invoke("/layouts/gridViewJson");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String[] getaColumns() {
        return aColumns;
    }

    public void setaColumns(String[] aColumns) {
        this.aColumns = aColumns;
    }

    public HashMap<String, Object> getGrid() {
        return grid;
    }

    public void setGrid(HashMap<String, Object> grid) {
        this.grid = grid;
    }

    public Class getController() {
        return c;
    }

    public void setController(Class c) {
        this.c = c;
    }
}
