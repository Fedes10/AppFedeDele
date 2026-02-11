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
import com.example.delefede.data.model.Noticia;
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
public final class NoticiaDao_Impl implements NoticiaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Noticia> __insertionAdapterOfNoticia;

  private final EntityDeletionOrUpdateAdapter<Noticia> __deletionAdapterOfNoticia;

  private final EntityDeletionOrUpdateAdapter<Noticia> __updateAdapterOfNoticia;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllNoticias;

  public NoticiaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNoticia = new EntityInsertionAdapter<Noticia>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `noticias` (`id`,`titulo`,`descripcion`,`contenido`,`imagenUrl`,`categoria`,`fecha`,`autor`,`imagenLocal`,`sincronizado`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Noticia entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitulo());
        statement.bindString(3, entity.getDescripcion());
        statement.bindString(4, entity.getContenido());
        statement.bindString(5, entity.getImagenUrl());
        statement.bindString(6, entity.getCategoria());
        statement.bindString(7, entity.getFecha());
        statement.bindString(8, entity.getAutor());
        if (entity.getImagenLocal() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImagenLocal());
        }
        final int _tmp = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(10, _tmp);
      }
    };
    this.__deletionAdapterOfNoticia = new EntityDeletionOrUpdateAdapter<Noticia>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `noticias` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Noticia entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfNoticia = new EntityDeletionOrUpdateAdapter<Noticia>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `noticias` SET `id` = ?,`titulo` = ?,`descripcion` = ?,`contenido` = ?,`imagenUrl` = ?,`categoria` = ?,`fecha` = ?,`autor` = ?,`imagenLocal` = ?,`sincronizado` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Noticia entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitulo());
        statement.bindString(3, entity.getDescripcion());
        statement.bindString(4, entity.getContenido());
        statement.bindString(5, entity.getImagenUrl());
        statement.bindString(6, entity.getCategoria());
        statement.bindString(7, entity.getFecha());
        statement.bindString(8, entity.getAutor());
        if (entity.getImagenLocal() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImagenLocal());
        }
        final int _tmp = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindString(11, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllNoticias = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM noticias";
        return _query;
      }
    };
  }

  @Override
  public Object insertNoticia(final Noticia noticia, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNoticia.insert(noticia);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAllNoticias(final List<Noticia> noticias,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNoticia.insert(noticias);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteNoticia(final Noticia noticia, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfNoticia.handle(noticia);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateNoticia(final Noticia noticia, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfNoticia.handle(noticia);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllNoticias(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllNoticias.acquire();
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
          __preparedStmtOfDeleteAllNoticias.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Noticia>> getAllNoticias() {
    final String _sql = "SELECT * FROM noticias ORDER BY fecha DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"noticias"}, new Callable<List<Noticia>>() {
      @Override
      @NonNull
      public List<Noticia> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfContenido = CursorUtil.getColumnIndexOrThrow(_cursor, "contenido");
          final int _cursorIndexOfImagenUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenUrl");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfAutor = CursorUtil.getColumnIndexOrThrow(_cursor, "autor");
          final int _cursorIndexOfImagenLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenLocal");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final List<Noticia> _result = new ArrayList<Noticia>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Noticia _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpContenido;
            _tmpContenido = _cursor.getString(_cursorIndexOfContenido);
            final String _tmpImagenUrl;
            _tmpImagenUrl = _cursor.getString(_cursorIndexOfImagenUrl);
            final String _tmpCategoria;
            _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            final String _tmpFecha;
            _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
            final String _tmpAutor;
            _tmpAutor = _cursor.getString(_cursorIndexOfAutor);
            final String _tmpImagenLocal;
            if (_cursor.isNull(_cursorIndexOfImagenLocal)) {
              _tmpImagenLocal = null;
            } else {
              _tmpImagenLocal = _cursor.getString(_cursorIndexOfImagenLocal);
            }
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
            _item = new Noticia(_tmpId,_tmpTitulo,_tmpDescripcion,_tmpContenido,_tmpImagenUrl,_tmpCategoria,_tmpFecha,_tmpAutor,_tmpImagenLocal,_tmpSincronizado);
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
  public Flow<List<Noticia>> getNoticiasByCategoria(final String categoria) {
    final String _sql = "SELECT * FROM noticias WHERE categoria = ? ORDER BY fecha DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, categoria);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"noticias"}, new Callable<List<Noticia>>() {
      @Override
      @NonNull
      public List<Noticia> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfContenido = CursorUtil.getColumnIndexOrThrow(_cursor, "contenido");
          final int _cursorIndexOfImagenUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenUrl");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfAutor = CursorUtil.getColumnIndexOrThrow(_cursor, "autor");
          final int _cursorIndexOfImagenLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenLocal");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final List<Noticia> _result = new ArrayList<Noticia>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Noticia _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpContenido;
            _tmpContenido = _cursor.getString(_cursorIndexOfContenido);
            final String _tmpImagenUrl;
            _tmpImagenUrl = _cursor.getString(_cursorIndexOfImagenUrl);
            final String _tmpCategoria;
            _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            final String _tmpFecha;
            _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
            final String _tmpAutor;
            _tmpAutor = _cursor.getString(_cursorIndexOfAutor);
            final String _tmpImagenLocal;
            if (_cursor.isNull(_cursorIndexOfImagenLocal)) {
              _tmpImagenLocal = null;
            } else {
              _tmpImagenLocal = _cursor.getString(_cursorIndexOfImagenLocal);
            }
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
            _item = new Noticia(_tmpId,_tmpTitulo,_tmpDescripcion,_tmpContenido,_tmpImagenUrl,_tmpCategoria,_tmpFecha,_tmpAutor,_tmpImagenLocal,_tmpSincronizado);
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
  public Object getNoticiaById(final String id, final Continuation<? super Noticia> $completion) {
    final String _sql = "SELECT * FROM noticias WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Noticia>() {
      @Override
      @Nullable
      public Noticia call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfContenido = CursorUtil.getColumnIndexOrThrow(_cursor, "contenido");
          final int _cursorIndexOfImagenUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenUrl");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfAutor = CursorUtil.getColumnIndexOrThrow(_cursor, "autor");
          final int _cursorIndexOfImagenLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenLocal");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final Noticia _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpContenido;
            _tmpContenido = _cursor.getString(_cursorIndexOfContenido);
            final String _tmpImagenUrl;
            _tmpImagenUrl = _cursor.getString(_cursorIndexOfImagenUrl);
            final String _tmpCategoria;
            _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            final String _tmpFecha;
            _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
            final String _tmpAutor;
            _tmpAutor = _cursor.getString(_cursorIndexOfAutor);
            final String _tmpImagenLocal;
            if (_cursor.isNull(_cursorIndexOfImagenLocal)) {
              _tmpImagenLocal = null;
            } else {
              _tmpImagenLocal = _cursor.getString(_cursorIndexOfImagenLocal);
            }
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
            _result = new Noticia(_tmpId,_tmpTitulo,_tmpDescripcion,_tmpContenido,_tmpImagenUrl,_tmpCategoria,_tmpFecha,_tmpAutor,_tmpImagenLocal,_tmpSincronizado);
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
  public Object getCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM noticias";
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
