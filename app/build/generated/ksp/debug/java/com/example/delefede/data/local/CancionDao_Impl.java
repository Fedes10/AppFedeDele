package com.example.delefede.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.delefede.data.model.Cancion;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CancionDao_Impl implements CancionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Cancion> __insertionAdapterOfCancion;

  private final EntityDeletionOrUpdateAdapter<Cancion> __deletionAdapterOfCancion;

  private final EntityDeletionOrUpdateAdapter<Cancion> __updateAdapterOfCancion;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCanciones;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAudioLocal;

  public CancionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCancion = new EntityInsertionAdapter<Cancion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `canciones` (`id`,`titulo`,`artista`,`categoria`,`letra`,`audioUrl`,`duracion`,`audioLocal`,`sincronizado`,`descargado`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Cancion entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitulo());
        statement.bindString(3, entity.getArtista());
        statement.bindString(4, entity.getCategoria());
        statement.bindString(5, entity.getLetra());
        statement.bindString(6, entity.getAudioUrl());
        statement.bindLong(7, entity.getDuracion());
        if (entity.getAudioLocal() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAudioLocal());
        }
        final int _tmp = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.getDescargado() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
      }
    };
    this.__deletionAdapterOfCancion = new EntityDeletionOrUpdateAdapter<Cancion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `canciones` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Cancion entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfCancion = new EntityDeletionOrUpdateAdapter<Cancion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `canciones` SET `id` = ?,`titulo` = ?,`artista` = ?,`categoria` = ?,`letra` = ?,`audioUrl` = ?,`duracion` = ?,`audioLocal` = ?,`sincronizado` = ?,`descargado` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Cancion entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitulo());
        statement.bindString(3, entity.getArtista());
        statement.bindString(4, entity.getCategoria());
        statement.bindString(5, entity.getLetra());
        statement.bindString(6, entity.getAudioUrl());
        statement.bindLong(7, entity.getDuracion());
        if (entity.getAudioLocal() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAudioLocal());
        }
        final int _tmp = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.getDescargado() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        statement.bindString(11, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllCanciones = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM canciones";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAudioLocal = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE canciones SET audioLocal = ?, descargado = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertCancion(final Cancion cancion, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCancion.insert(cancion);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAllCanciones(final List<Cancion> canciones,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCancion.insert(canciones);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCancion(final Cancion cancion, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCancion.handle(cancion);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateCancion(final Cancion cancion, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCancion.handle(cancion);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllCanciones(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCanciones.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllCanciones.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAudioLocal(final String id, final String localPath,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAudioLocal.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, localPath);
        _argIndex = 2;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateAudioLocal.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Cancion>> getAllCanciones() {
    final String _sql = "SELECT * FROM canciones ORDER BY categoria, titulo";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"canciones"}, new Callable<List<Cancion>>() {
      @Override
      @NonNull
      public List<Cancion> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "artista");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfLetra = CursorUtil.getColumnIndexOrThrow(_cursor, "letra");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfDuracion = CursorUtil.getColumnIndexOrThrow(_cursor, "duracion");
          final int _cursorIndexOfAudioLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "audioLocal");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final int _cursorIndexOfDescargado = CursorUtil.getColumnIndexOrThrow(_cursor, "descargado");
          final List<Cancion> _result = new ArrayList<Cancion>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Cancion _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpArtista;
            _tmpArtista = _cursor.getString(_cursorIndexOfArtista);
            final String _tmpCategoria;
            _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            final String _tmpLetra;
            _tmpLetra = _cursor.getString(_cursorIndexOfLetra);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            final int _tmpDuracion;
            _tmpDuracion = _cursor.getInt(_cursorIndexOfDuracion);
            final String _tmpAudioLocal;
            if (_cursor.isNull(_cursorIndexOfAudioLocal)) {
              _tmpAudioLocal = null;
            } else {
              _tmpAudioLocal = _cursor.getString(_cursorIndexOfAudioLocal);
            }
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
            final boolean _tmpDescargado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfDescargado);
            _tmpDescargado = _tmp_1 != 0;
            _item = new Cancion(_tmpId,_tmpTitulo,_tmpArtista,_tmpCategoria,_tmpLetra,_tmpAudioUrl,_tmpDuracion,_tmpAudioLocal,_tmpSincronizado,_tmpDescargado);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Cancion>> getCancionesByCategoria(final String categoria) {
    final String _sql = "SELECT * FROM canciones WHERE categoria = ? ORDER BY titulo";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, categoria);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"canciones"}, new Callable<List<Cancion>>() {
      @Override
      @NonNull
      public List<Cancion> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "artista");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfLetra = CursorUtil.getColumnIndexOrThrow(_cursor, "letra");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfDuracion = CursorUtil.getColumnIndexOrThrow(_cursor, "duracion");
          final int _cursorIndexOfAudioLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "audioLocal");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final int _cursorIndexOfDescargado = CursorUtil.getColumnIndexOrThrow(_cursor, "descargado");
          final List<Cancion> _result = new ArrayList<Cancion>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Cancion _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpArtista;
            _tmpArtista = _cursor.getString(_cursorIndexOfArtista);
            final String _tmpCategoria;
            _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            final String _tmpLetra;
            _tmpLetra = _cursor.getString(_cursorIndexOfLetra);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            final int _tmpDuracion;
            _tmpDuracion = _cursor.getInt(_cursorIndexOfDuracion);
            final String _tmpAudioLocal;
            if (_cursor.isNull(_cursorIndexOfAudioLocal)) {
              _tmpAudioLocal = null;
            } else {
              _tmpAudioLocal = _cursor.getString(_cursorIndexOfAudioLocal);
            }
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
            final boolean _tmpDescargado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfDescargado);
            _tmpDescargado = _tmp_1 != 0;
            _item = new Cancion(_tmpId,_tmpTitulo,_tmpArtista,_tmpCategoria,_tmpLetra,_tmpAudioUrl,_tmpDuracion,_tmpAudioLocal,_tmpSincronizado,_tmpDescargado);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<String>> getCategorias() {
    final String _sql = "SELECT DISTINCT categoria FROM canciones ORDER BY categoria";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"canciones"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCancionById(final String id, final Continuation<? super Cancion> $completion) {
    final String _sql = "SELECT * FROM canciones WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Cancion>() {
      @Override
      @Nullable
      public Cancion call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "artista");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfLetra = CursorUtil.getColumnIndexOrThrow(_cursor, "letra");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfDuracion = CursorUtil.getColumnIndexOrThrow(_cursor, "duracion");
          final int _cursorIndexOfAudioLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "audioLocal");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final int _cursorIndexOfDescargado = CursorUtil.getColumnIndexOrThrow(_cursor, "descargado");
          final Cancion _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpArtista;
            _tmpArtista = _cursor.getString(_cursorIndexOfArtista);
            final String _tmpCategoria;
            _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            final String _tmpLetra;
            _tmpLetra = _cursor.getString(_cursorIndexOfLetra);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            final int _tmpDuracion;
            _tmpDuracion = _cursor.getInt(_cursorIndexOfDuracion);
            final String _tmpAudioLocal;
            if (_cursor.isNull(_cursorIndexOfAudioLocal)) {
              _tmpAudioLocal = null;
            } else {
              _tmpAudioLocal = _cursor.getString(_cursorIndexOfAudioLocal);
            }
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
            final boolean _tmpDescargado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfDescargado);
            _tmpDescargado = _tmp_1 != 0;
            _result = new Cancion(_tmpId,_tmpTitulo,_tmpArtista,_tmpCategoria,_tmpLetra,_tmpAudioUrl,_tmpDuracion,_tmpAudioLocal,_tmpSincronizado,_tmpDescargado);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Cancion>> searchCanciones(final String query) {
    final String _sql = "SELECT * FROM canciones WHERE titulo LIKE '%' || ? || '%' OR artista LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"canciones"}, new Callable<List<Cancion>>() {
      @Override
      @NonNull
      public List<Cancion> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "artista");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfLetra = CursorUtil.getColumnIndexOrThrow(_cursor, "letra");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfDuracion = CursorUtil.getColumnIndexOrThrow(_cursor, "duracion");
          final int _cursorIndexOfAudioLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "audioLocal");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final int _cursorIndexOfDescargado = CursorUtil.getColumnIndexOrThrow(_cursor, "descargado");
          final List<Cancion> _result = new ArrayList<Cancion>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Cancion _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpArtista;
            _tmpArtista = _cursor.getString(_cursorIndexOfArtista);
            final String _tmpCategoria;
            _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            final String _tmpLetra;
            _tmpLetra = _cursor.getString(_cursorIndexOfLetra);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            final int _tmpDuracion;
            _tmpDuracion = _cursor.getInt(_cursorIndexOfDuracion);
            final String _tmpAudioLocal;
            if (_cursor.isNull(_cursorIndexOfAudioLocal)) {
              _tmpAudioLocal = null;
            } else {
              _tmpAudioLocal = _cursor.getString(_cursorIndexOfAudioLocal);
            }
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
            final boolean _tmpDescargado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfDescargado);
            _tmpDescargado = _tmp_1 != 0;
            _item = new Cancion(_tmpId,_tmpTitulo,_tmpArtista,_tmpCategoria,_tmpLetra,_tmpAudioUrl,_tmpDuracion,_tmpAudioLocal,_tmpSincronizado,_tmpDescargado);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM canciones";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
