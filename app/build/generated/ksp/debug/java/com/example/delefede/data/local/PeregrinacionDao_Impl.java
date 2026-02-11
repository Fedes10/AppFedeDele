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
import com.example.delefede.data.model.Peregrinacion;
import java.lang.Class;
import java.lang.Exception;
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
public final class PeregrinacionDao_Impl implements PeregrinacionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Peregrinacion> __insertionAdapterOfPeregrinacion;

  private final EntityDeletionOrUpdateAdapter<Peregrinacion> __deletionAdapterOfPeregrinacion;

  private final EntityDeletionOrUpdateAdapter<Peregrinacion> __updateAdapterOfPeregrinacion;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPeregrinaciones;

  public PeregrinacionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPeregrinacion = new EntityInsertionAdapter<Peregrinacion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `peregrinaciones` (`id`,`nombre`,`descripcion`,`fechaSalida`,`fechaRegreso`,`precio`,`requisitos`,`inscripcionApertura`,`inscripcionCierre`,`plazas`,`enlaceInscripcion`,`imagenPortadaUrl`,`imagenesUrls`,`colorTema`,`imagenPortadaLocal`,`sincronizado`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Peregrinacion entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getNombre());
        statement.bindString(3, entity.getDescripcion());
        statement.bindString(4, entity.getFechaSalida());
        statement.bindString(5, entity.getFechaRegreso());
        statement.bindString(6, entity.getPrecio());
        statement.bindString(7, entity.getRequisitos());
        statement.bindString(8, entity.getInscripcionApertura());
        statement.bindString(9, entity.getInscripcionCierre());
        statement.bindString(10, entity.getPlazas());
        statement.bindString(11, entity.getEnlaceInscripcion());
        statement.bindString(12, entity.getImagenPortadaUrl());
        statement.bindString(13, entity.getImagenesUrls());
        statement.bindString(14, entity.getColorTema());
        if (entity.getImagenPortadaLocal() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getImagenPortadaLocal());
        }
        final int _tmp = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(16, _tmp);
      }
    };
    this.__deletionAdapterOfPeregrinacion = new EntityDeletionOrUpdateAdapter<Peregrinacion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `peregrinaciones` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Peregrinacion entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfPeregrinacion = new EntityDeletionOrUpdateAdapter<Peregrinacion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `peregrinaciones` SET `id` = ?,`nombre` = ?,`descripcion` = ?,`fechaSalida` = ?,`fechaRegreso` = ?,`precio` = ?,`requisitos` = ?,`inscripcionApertura` = ?,`inscripcionCierre` = ?,`plazas` = ?,`enlaceInscripcion` = ?,`imagenPortadaUrl` = ?,`imagenesUrls` = ?,`colorTema` = ?,`imagenPortadaLocal` = ?,`sincronizado` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Peregrinacion entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getNombre());
        statement.bindString(3, entity.getDescripcion());
        statement.bindString(4, entity.getFechaSalida());
        statement.bindString(5, entity.getFechaRegreso());
        statement.bindString(6, entity.getPrecio());
        statement.bindString(7, entity.getRequisitos());
        statement.bindString(8, entity.getInscripcionApertura());
        statement.bindString(9, entity.getInscripcionCierre());
        statement.bindString(10, entity.getPlazas());
        statement.bindString(11, entity.getEnlaceInscripcion());
        statement.bindString(12, entity.getImagenPortadaUrl());
        statement.bindString(13, entity.getImagenesUrls());
        statement.bindString(14, entity.getColorTema());
        if (entity.getImagenPortadaLocal() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getImagenPortadaLocal());
        }
        final int _tmp = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(16, _tmp);
        statement.bindString(17, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllPeregrinaciones = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM peregrinaciones";
        return _query;
      }
    };
  }

  @Override
  public Object insertPeregrinacion(final Peregrinacion peregrinacion,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPeregrinacion.insert(peregrinacion);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAllPeregrinaciones(final List<Peregrinacion> peregrinaciones,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPeregrinacion.insert(peregrinaciones);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePeregrinacion(final Peregrinacion peregrinacion,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPeregrinacion.handle(peregrinacion);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePeregrinacion(final Peregrinacion peregrinacion,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPeregrinacion.handle(peregrinacion);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllPeregrinaciones(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPeregrinaciones.acquire();
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
          __preparedStmtOfDeleteAllPeregrinaciones.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Peregrinacion>> getAllPeregrinaciones() {
    final String _sql = "SELECT * FROM peregrinaciones ORDER BY fechaSalida";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"peregrinaciones"}, new Callable<List<Peregrinacion>>() {
      @Override
      @NonNull
      public List<Peregrinacion> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfFechaSalida = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaSalida");
          final int _cursorIndexOfFechaRegreso = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaRegreso");
          final int _cursorIndexOfPrecio = CursorUtil.getColumnIndexOrThrow(_cursor, "precio");
          final int _cursorIndexOfRequisitos = CursorUtil.getColumnIndexOrThrow(_cursor, "requisitos");
          final int _cursorIndexOfInscripcionApertura = CursorUtil.getColumnIndexOrThrow(_cursor, "inscripcionApertura");
          final int _cursorIndexOfInscripcionCierre = CursorUtil.getColumnIndexOrThrow(_cursor, "inscripcionCierre");
          final int _cursorIndexOfPlazas = CursorUtil.getColumnIndexOrThrow(_cursor, "plazas");
          final int _cursorIndexOfEnlaceInscripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "enlaceInscripcion");
          final int _cursorIndexOfImagenPortadaUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenPortadaUrl");
          final int _cursorIndexOfImagenesUrls = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenesUrls");
          final int _cursorIndexOfColorTema = CursorUtil.getColumnIndexOrThrow(_cursor, "colorTema");
          final int _cursorIndexOfImagenPortadaLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenPortadaLocal");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final List<Peregrinacion> _result = new ArrayList<Peregrinacion>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Peregrinacion _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpFechaSalida;
            _tmpFechaSalida = _cursor.getString(_cursorIndexOfFechaSalida);
            final String _tmpFechaRegreso;
            _tmpFechaRegreso = _cursor.getString(_cursorIndexOfFechaRegreso);
            final String _tmpPrecio;
            _tmpPrecio = _cursor.getString(_cursorIndexOfPrecio);
            final String _tmpRequisitos;
            _tmpRequisitos = _cursor.getString(_cursorIndexOfRequisitos);
            final String _tmpInscripcionApertura;
            _tmpInscripcionApertura = _cursor.getString(_cursorIndexOfInscripcionApertura);
            final String _tmpInscripcionCierre;
            _tmpInscripcionCierre = _cursor.getString(_cursorIndexOfInscripcionCierre);
            final String _tmpPlazas;
            _tmpPlazas = _cursor.getString(_cursorIndexOfPlazas);
            final String _tmpEnlaceInscripcion;
            _tmpEnlaceInscripcion = _cursor.getString(_cursorIndexOfEnlaceInscripcion);
            final String _tmpImagenPortadaUrl;
            _tmpImagenPortadaUrl = _cursor.getString(_cursorIndexOfImagenPortadaUrl);
            final String _tmpImagenesUrls;
            _tmpImagenesUrls = _cursor.getString(_cursorIndexOfImagenesUrls);
            final String _tmpColorTema;
            _tmpColorTema = _cursor.getString(_cursorIndexOfColorTema);
            final String _tmpImagenPortadaLocal;
            if (_cursor.isNull(_cursorIndexOfImagenPortadaLocal)) {
              _tmpImagenPortadaLocal = null;
            } else {
              _tmpImagenPortadaLocal = _cursor.getString(_cursorIndexOfImagenPortadaLocal);
            }
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
            _item = new Peregrinacion(_tmpId,_tmpNombre,_tmpDescripcion,_tmpFechaSalida,_tmpFechaRegreso,_tmpPrecio,_tmpRequisitos,_tmpInscripcionApertura,_tmpInscripcionCierre,_tmpPlazas,_tmpEnlaceInscripcion,_tmpImagenPortadaUrl,_tmpImagenesUrls,_tmpColorTema,_tmpImagenPortadaLocal,_tmpSincronizado);
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
  public Object getPeregrinacionById(final String id,
      final Continuation<? super Peregrinacion> $completion) {
    final String _sql = "SELECT * FROM peregrinaciones WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Peregrinacion>() {
      @Override
      @Nullable
      public Peregrinacion call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfFechaSalida = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaSalida");
          final int _cursorIndexOfFechaRegreso = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaRegreso");
          final int _cursorIndexOfPrecio = CursorUtil.getColumnIndexOrThrow(_cursor, "precio");
          final int _cursorIndexOfRequisitos = CursorUtil.getColumnIndexOrThrow(_cursor, "requisitos");
          final int _cursorIndexOfInscripcionApertura = CursorUtil.getColumnIndexOrThrow(_cursor, "inscripcionApertura");
          final int _cursorIndexOfInscripcionCierre = CursorUtil.getColumnIndexOrThrow(_cursor, "inscripcionCierre");
          final int _cursorIndexOfPlazas = CursorUtil.getColumnIndexOrThrow(_cursor, "plazas");
          final int _cursorIndexOfEnlaceInscripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "enlaceInscripcion");
          final int _cursorIndexOfImagenPortadaUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenPortadaUrl");
          final int _cursorIndexOfImagenesUrls = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenesUrls");
          final int _cursorIndexOfColorTema = CursorUtil.getColumnIndexOrThrow(_cursor, "colorTema");
          final int _cursorIndexOfImagenPortadaLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenPortadaLocal");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final Peregrinacion _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpFechaSalida;
            _tmpFechaSalida = _cursor.getString(_cursorIndexOfFechaSalida);
            final String _tmpFechaRegreso;
            _tmpFechaRegreso = _cursor.getString(_cursorIndexOfFechaRegreso);
            final String _tmpPrecio;
            _tmpPrecio = _cursor.getString(_cursorIndexOfPrecio);
            final String _tmpRequisitos;
            _tmpRequisitos = _cursor.getString(_cursorIndexOfRequisitos);
            final String _tmpInscripcionApertura;
            _tmpInscripcionApertura = _cursor.getString(_cursorIndexOfInscripcionApertura);
            final String _tmpInscripcionCierre;
            _tmpInscripcionCierre = _cursor.getString(_cursorIndexOfInscripcionCierre);
            final String _tmpPlazas;
            _tmpPlazas = _cursor.getString(_cursorIndexOfPlazas);
            final String _tmpEnlaceInscripcion;
            _tmpEnlaceInscripcion = _cursor.getString(_cursorIndexOfEnlaceInscripcion);
            final String _tmpImagenPortadaUrl;
            _tmpImagenPortadaUrl = _cursor.getString(_cursorIndexOfImagenPortadaUrl);
            final String _tmpImagenesUrls;
            _tmpImagenesUrls = _cursor.getString(_cursorIndexOfImagenesUrls);
            final String _tmpColorTema;
            _tmpColorTema = _cursor.getString(_cursorIndexOfColorTema);
            final String _tmpImagenPortadaLocal;
            if (_cursor.isNull(_cursorIndexOfImagenPortadaLocal)) {
              _tmpImagenPortadaLocal = null;
            } else {
              _tmpImagenPortadaLocal = _cursor.getString(_cursorIndexOfImagenPortadaLocal);
            }
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
            _result = new Peregrinacion(_tmpId,_tmpNombre,_tmpDescripcion,_tmpFechaSalida,_tmpFechaRegreso,_tmpPrecio,_tmpRequisitos,_tmpInscripcionApertura,_tmpInscripcionCierre,_tmpPlazas,_tmpEnlaceInscripcion,_tmpImagenPortadaUrl,_tmpImagenesUrls,_tmpColorTema,_tmpImagenPortadaLocal,_tmpSincronizado);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
