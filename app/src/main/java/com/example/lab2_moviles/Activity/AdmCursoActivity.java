package com.example.lab2_moviles.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_moviles.AccesoDatos.AsyncTaskManager;
import com.example.lab2_moviles.AccesoDatos.Data;
import com.example.lab2_moviles.Adapter.CursoAdapter;
import com.example.lab2_moviles.Helper.RecyclerItemTouchHelper;
import com.example.lab2_moviles.LogicaNegocio.Curso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.lab2_moviles.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class AdmCursoActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, CursoAdapter.CursoAdapterListener {
    private RecyclerView mRecyclerView;
    private CursoAdapter mAdapter;
    private List<Curso> cursoList;
    private CoordinatorLayout coordinatorLayout;
    private SearchView searchView;
    private FloatingActionButton fab;
    private Data model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_curso);
        Toolbar toolbar = findViewById(R.id.toolbarC);
        setSupportActionBar(toolbar);

        this.coordinatorLayout = findViewById(R.id.coordinator_layout_curso);

        getSupportActionBar().setTitle(getString(R.string.curso));
        mRecyclerView = findViewById(R.id.recycler_cursosFld);







        cursoList = new ArrayList<>();

        //model= new Data();
        //cursoList= model.getCursoList();

        AsyncTaskManager net = new AsyncTaskManager("http://10.0.2.2:36083/frontend_web/servletCursos", new AsyncTaskManager.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    JSONArray array = new JSONArray(output);
                    //carreraList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        Curso c = new Curso(
                        array.getJSONObject(i).getString("codigo"),
                        array.getJSONObject(i).getString("nombre"),
                        array.getJSONObject(i).getInt("creditos"),
                        array.getJSONObject(i).getInt("horasSemanales")
                        );
                        c.setId(array.getJSONObject(i).getInt("id"));
                        cursoList.add(c);
                    }
                    mAdapter = new CursoAdapter(cursoList, AdmCursoActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                    intentInformation();
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        net.execute(AsyncTaskManager.GET);
        whiteNotificationBar(mRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        fab = findViewById(R.id.AddCurso);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddUpdCurso();
            }
        });
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);


        //mAdapter.notifyDataSetChanged();
    }




    public void intentInformation(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){ // si extras recibio algun objeto
            Curso auxiliar;
            auxiliar = (Curso)getIntent().getSerializableExtra("addCurso");
            if(auxiliar != null){ // add curso trae algun elemento, agregar nuevo
                //this.model.getCursoList().add(auxiliar);
                cursoList.add(auxiliar);
                Toast.makeText(getApplicationContext(), auxiliar.getNombre() + " agregado correctamente", Toast.LENGTH_LONG).show();
            }else{ // se esta editando un profesor
                auxiliar = (Curso)getIntent().getSerializableExtra("editCurso");
                boolean founded = false;
                for (Curso c1 : cursoList) {
                    Toast.makeText(getApplicationContext(), auxiliar.getCodigo() + " vs "+c1.getCodigo(), Toast.LENGTH_LONG).show();
                    if (c1.getCodigo().equals(auxiliar.getCodigo())) {
                        c1.setNombre(auxiliar.getNombre());
                        c1.setHoras(auxiliar.getHoras());
                        c1.setCreditos(auxiliar.getCreditos());
                        founded = true;
                        break;
                    }
                }
                if (founded) {
                    Toast.makeText(getApplicationContext(), auxiliar.getNombre() + " editado correctamente", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), auxiliar.getNombre() + " no encontrado", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private void goToAddUpdCurso() {
        Intent intent = new Intent(this, AddUpdCursoActivity.class);
        intent.putExtra("editable", false);
        startActivity(intent);
    }
    @Override
    public void onContactSelected(Curso curso) {
        Toast.makeText(getApplicationContext(), "Selected: " + curso.getCodigo() + ", " + curso.getNombre(), Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change, every type on input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof CursoAdapter.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String name = cursoList.get(viewHolder.getAdapterPosition()).getNombre();
                int id =  cursoList.get(viewHolder.getAdapterPosition()).getId();
                // save the index deleted
                String aux = "http://10.0.2.2:36083/frontend_web/servletCursos?" +
                        "x="+id;
                AsyncTaskManager net = new AsyncTaskManager(aux, new AsyncTaskManager.AsyncResponse() {

                    @Override
                    public void processFinish(String output) {

                    }
                });
                net.execute(AsyncTaskManager.DELETE);
                final int deletedIndex = viewHolder.getAdapterPosition();
                // remove the item from recyclerView
                mAdapter.removeItem(viewHolder.getAdapterPosition());

                // showing snack bar with Undo option
                Snackbar snackbar = Snackbar.make(coordinatorLayout, name + " removido!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // undo is selected, restore the deleted item from adapter
                        mAdapter.restoreItem(deletedIndex);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        } else {
            Curso aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            //send data to Edit Activity
            Intent intent = new Intent(this, AddUpdCursoActivity.class);
            intent.putExtra("editable", true);
            intent.putExtra("curso", aux);
            mAdapter.notifyDataSetChanged(); //restart left swipe view
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        Intent a = new Intent(this, NavDrawerActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        super.onBackPressed();
    }
    @Override
    public void onItemMove(int source, int target) {
        mAdapter.onItemMove(source, target);
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
