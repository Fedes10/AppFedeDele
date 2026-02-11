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
import com.example.delefede.data.model.Evento;
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
public final class EventoDao_Impl implements EventoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Evento> __insertionAdapterOfEvento;

  private final EntityDeletionOrUpdateAdapter<Evento> __deletionAdapterOfEvento;

  private final EntityDeletionOrUpdateAdapter<Evento> __updateAdapterOfEvento;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllEventos;

  public EventoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEvento = new EntityInsertionAdapter<Evento>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `eventos` (`id`,`titulo`,`descripcion`,`fechaInicio`,`fechaFin`,`horaInicio`,`horaFin`,`ubicacion`,`tipo`,`color`,`imagen`,`imagenLocal`,`enlaceNoticia`,`recordatorio`,`sincronizado`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Evento entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitulo());
        statement.bindString(3, entity.getDescripcion());
        statement.bindString(4, entity.getFechaInicio());
        if (entity.getFechaFin() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFechaFin());
        }
        if (entity.getHoraInicio() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getHoraInicio());
        }
        if (entity.getHoraFin() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getHoraFin());
        }
        statement.bindString(8, entity.getUbicacion());
        statement.bindString(9, entity.getTipo());
        statement.bindString(10, entity.getColor());
        if (entity.getImagen() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getImagen());
        }
        if (entity.getImagenLocal() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getImagenLocal());
        }
        if (entity.getEnlaceNoticia() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getEnlaceNoticia());
        }
        final int _tmp = entity.getRecordatorio() ? 1 : 0;
        statement.bindLong(14, _tmp);
        final int _tmp_1 = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(15, _tmp_1);
      }
    };
    this.__deletionAdapterOfEvento = new EntityDeletionOrUpdateAdapter<Evento>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `eventos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Evento entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfEvento = new EntityDeletionOrUpdateAdapter<Evento>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `eventos` SET `id` = ?,`titulo` = ?,`descripcion` = ?,`fechaInicio` = ?,`fechaFin` = ?,`horaInicio` = ?,`horaFin` = ?,`ubicacion` = ?,`tipo` = ?,`color` = ?,`imagen` = ?,`imagenLocal` = ?,`enlaceNoticia` = ?,`recordatorio` = ?,`sincronizado` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Evento entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitulo());
        statement.bindString(3, entity.getDescripcion());
        statement.bindString(4, entity.getFechaInicio());
        if (entity.getFechaFin() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFechaFin());
        }
        if (entity.getHoraInicio() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getHoraInicio());
        }
        if (entity.getHoraFin() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getHoraFin());
        }
        statement.bindString(8, entity.getUbicacion());
        statement.bindString(9, entity.getTipo());
        statement.bindString(10, entity.getColor());
        if (entity.getImagen() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getImagen());
        }
        if (entity.getImagenLocal() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getImagenLocal());
        }
        if (entity.getEnlaceNoticia() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getEnlaceNoticia());
        }
        final int _tmp = entity.getRecordatorio() ? 1 : 0;
        statement.bindLong(14, _tmp);
        final int _tmp_1 = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(15, _tmp_1);
        statement.bindString(16, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllEventos = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM eventos";
        return _query;
      }
    };
  }

  @Override
  public Object insertEvento(final Evento evento, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEvento.insert(evento);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAllEventos(final List<Evento> eventos,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEvento.insert(eventos);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteEvento(final Evento evento, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfEvento.handle(evento);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateEvento(final Evento evento, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfEvento.handle(evento);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllEventos(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllEventos.acquire();
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
          __preparedStmtOfDeleteAllEventos.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Evento>> getAllEventos() {
    final String _sql = "SELECT * FROM eventos ORDER BY fechaInicio, horaInicio";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"eventos"}, new Callable<List<Evento>>() {
      @Override
      @NonNull
      public List<Evento> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfFechaInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaInicio");
          final int _cursorIndexOfFechaFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaFin");
          final int _cursorIndexOfHoraInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "horaInicio");
          final int _cursorIndexOfHoraFin = CursorUtil.getColumnIndexOrThrow(_cursor, "horaFin");
          final int _cursorIndexOfUbicacion = CursorUtil.getColumnIndexOrThrow(_cursor, "ubicacion");
          final int _cursorIndexOfTipo = CursorUtil.getColumnIndexOrThrow(_cursor, "tipo");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfImagen = CursorUtil.getColumnIndexOrThrow(_cursor, "imagen");
          final int _cursorIndexOfImagenLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenLocal");
          final int _cursorIndexOfEnlaceNoticia = CursorUtil.getColumnIndexOrThrow(_cursor, "enlaceNoticia");
          final int _cursorIndexOfRecordatorio = CursorUtil.getColumnIndexOrThrow(_cursor, "recordatorio");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final List<Evento> _result = new ArrayList<Evento>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Evento _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpFechaInicio;
            _tmpFechaInicio = _cursor.getString(_cursorIndexOfFechaInicio);
            final String _tmpFechaFin;
            if (_cursor.isNull(_cursorIndexOfFechaFin)) {
              _tmpFechaFin = null;
            } else {
              _tmpFechaFin = _cursor.getString(_cursorIndexOfFechaFin);
            }
            final String _tmpHoraInicio;
            if (_cursor.isNull(_cursorIndexOfHoraInicio)) {
              _tmpHoraInicio = null;
            } else {
              _tmpHoraInicio = _cursor.getString(_cursorIndexOfHoraInicio);
            }
            final String _tmpHoraFin;
            if (_cursor.isNull(_cursorIndexOfHoraFin)) {
              _tmpHoraFin = null;
            } else {
              _tmpHoraFin = _cursor.getString(_cursorIndexOfHoraFin);
            }
            final String _tmpUbicacion;
            _tmpUbicacion = _cursor.getString(_cursorIndexOfUbicacion);
            final String _tmpTipo;
            _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            final String _tmpImagen;
            if (_cursor.isNull(_cursorIndexOfImagen)) {
              _tmpImagen = null;
            } else {
              _tmpImagen = _cursor.getString(_cursorIndexOfImagen);
            }
            final String _tmpImagenLocal;
            if (_cursor.isNull(_cursorIndexOfImagenLocal)) {
              _tmpImagenLocal = null;
            } else {
              _tmpImagenLocal = _cursor.getString(_cursorIndexOfImagenLocal);
            }
            final String _tmpEnlaceNoticia;
            if (_cursor.isNull(_cursorIndexOfEnlaceNoticia)) {
              _tmpEnlaceNoticia = null;
            } else {
              _tmpEnlaceNoticia = _cursor.getString(_cursorIndexOfEnlaceNoticia);
            }
            final boolean _tmpRecordatorio;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfRecordatorio);
            _tmpRecordatorio = _tmp != 0;
            final boolean _tmpSincronizado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp_1 != 0;
            _item = new Evento(_tmpId,_tmpTitulo,_tmpDescripcion,_tmpFechaInicio,_tmpFechaFin,_tmpHoraInicio,_tmpHoraFin,_tmpUbicacion,_tmpTipo,_tmpColor,_tmpImagen,_tmpImagenLocal,_tmpEnlaceNoticia,_tmpRecordatorio,_tmpSincronizado);
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
  public Flow<List<Evento>> getEventosByFecha(final String fecha) {
    final String _sql = "SELECT * FROM eventos WHERE fechaInicio = ? ORDER BY horaInicio";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, fecha);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"eventos"}, new Callable<List<Evento>>() {
      @Override
      @NonNull
      public List<Evento> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfFechaInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaInicio");
          final int _cursorIndexOfFechaFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaFin");
          final int _cursorIndexOfHoraInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "horaInicio");
          final int _cursorIndexOfHoraFin = CursorUtil.getColumnIndexOrThrow(_cursor, "horaFin");
          final int _cursorIndexOfUbicacion = CursorUtil.getColumnIndexOrThrow(_cursor, "ubicacion");
          final int _cursorIndexOfTipo = CursorUtil.getColumnIndexOrThrow(_cursor, "tipo");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfImagen = CursorUtil.getColumnIndexOrThrow(_cursor, "imagen");
          final int _cursorIndexOfImagenLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenLocal");
          final int _cursorIndexOfEnlaceNoticia = CursorUtil.getColumnIndexOrThrow(_cursor, "enlaceNoticia");
          final int _cursorIndexOfRecordatorio = CursorUtil.getColumnIndexOrThrow(_cursor, "recordatorio");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final List<Evento> _result = new ArrayList<Evento>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Evento _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpFechaInicio;
            _tmpFechaInicio = _cursor.getString(_cursorIndexOfFechaInicio);
            final String _tmpFechaFin;
            if (_cursor.isNull(_cursorIndexOfFechaFin)) {
              _tmpFechaFin = null;
            } else {
              _tmpFechaFin = _cursor.getString(_cursorIndexOfFechaFin);
            }
            final String _tmpHoraInicio;
            if (_cursor.isNull(_cursorIndexOfHoraInicio)) {
              _tmpHoraInicio = null;
            } else {
              _tmpHoraInicio = _cursor.getString(_cursorIndexOfHoraInicio);
            }
            final String _tmpHoraFin;
            if (_cursor.isNull(_cursorIndexOfHoraFin)) {
              _tmpHoraFin = null;
            } else {
              _tmpHoraFin = _cursor.getString(_cursorIndexOfHoraFin);
            }
            final String _tmpUbicacion;
            _tmpUbicacion = _cursor.getString(_cursorIndexOfUbicacion);
            final String _tmpTipo;
            _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            final String _tmpImagen;
            if (_cursor.isNull(_cursorIndexOfImagen)) {
              _tmpImagen = null;
            } else {
              _tmpImagen = _cursor.getString(_cursorIndexOfImagen);
            }
            final String _tmpImagenLocal;
            if (_cursor.isNull(_cursorIndexOfImagenLocal)) {
              _tmpImagenLocal = null;
            } else {
              _tmpImagenLocal = _cursor.getString(_cursorIndexOfImagenLocal);
            }
            final String _tmpEnlaceNoticia;
            if (_cursor.isNull(_cursorIndexOfEnlaceNoticia)) {
              _tmpEnlaceNoticia = null;
            } else {
              _tmpEnlaceNoticia = _cursor.getString(_cursorIndexOfEnlaceNoticia);
            }
            final boolean _tmpRecordatorio;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfRecordatorio);
            _tmpRecordatorio = _tmp != 0;
            final boolean _tmpSincronizado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp_1 != 0;
            _item = new Evento(_tmpId,_tmpTitulo,_tmpDescripcion,_tmpFechaInicio,_tmpFechaFin,_tmpHoraInicio,_tmpHoraFin,_tmpUbicacion,_tmpTipo,_tmpColor,_tmpImagen,_tmpImagenLocal,_tmpEnlaceNoticia,_tmpRecordatorio,_tmpSincronizado);
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
  public Flow<List<Evento>> getEventosBetweenDates(final String fechaInicio,
      final String fechaFin) {
    final String _sql = "SELECT * FROM eventos WHERE fechaInicio BETWEEN ? AND ? ORDER BY fechaInicio, horaInicio";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, fechaInicio);
    _argIndex = 2;
    _statement.bindString(_argIndex, fechaFin);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"eventos"}, new Callable<List<Evento>>() {
      @Override
      @NonNull
      public List<Evento> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfFechaInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaInicio");
          final int _cursorIndexOfFechaFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaFin");
          final int _cursorIndexOfHoraInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "horaInicio");
          final int _cursorIndexOfHoraFin = CursorUtil.getColumnIndexOrThrow(_cursor, "horaFin");
          final int _cursorIndexOfUbicacion = CursorUtil.getColumnIndexOrThrow(_cursor, "ubicacion");
          final int _cursorIndexOfTipo = CursorUtil.getColumnIndexOrThrow(_cursor, "tipo");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfImagen = CursorUtil.getColumnIndexOrThrow(_cursor, "imagen");
          final int _cursorIndexOfImagenLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenLocal");
          final int _cursorIndexOfEnlaceNoticia = CursorUtil.getColumnIndexOrThrow(_cursor, "enlaceNoticia");
          final int _cursorIndexOfRecordatorio = CursorUtil.getColumnIndexOrThrow(_cursor, "recordatorio");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final List<Evento> _result = new ArrayList<Evento>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Evento _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpFechaInicio;
            _tmpFechaInicio = _cursor.getString(_cursorIndexOfFechaInicio);
            final String _tmpFechaFin;
            if (_cursor.isNull(_cursorIndexOfFechaFin)) {
              _tmpFechaFin = null;
            } else {
              _tmpFechaFin = _cursor.getString(_cursorIndexOfFechaFin);
            }
            final String _tmpHoraInicio;
            if (_cursor.isNull(_cursorIndexOfHoraInicio)) {
              _tmpHoraInicio = null;
            } else {
              _tmpHoraInicio = _cursor.getString(_cursorIndexOfHoraInicio);
            }
            final String _tmpHoraFin;
            if (_cursor.isNull(_cursorIndexOfHoraFin)) {
              _tmpHoraFin = null;
            } else {
              _tmpHoraFin = _cursor.getString(_cursorIndexOfHoraFin);
            }
            final String _tmpUbicacion;
            _tmpUbicacion = _cursor.getString(_cursorIndexOfUbicacion);
            final String _tmpTipo;
            _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            final String _tmpImagen;
            if (_cursor.isNull(_cursorIndexOfImagen)) {
              _tmpImagen = null;
            } else {
              _tmpImagen = _cursor.getString(_cursorIndexOfImagen);
            }
            final String _tmpImagenLocal;
            if (_cursor.isNull(_cursorIndexOfImagenLocal)) {
              _tmpImagenLocal = null;
            } else {
              _tmpImagenLocal = _cursor.getString(_cursorIndexOfImagenLocal);
            }
            final String _tmpEnlaceNoticia;
            if (_cursor.isNull(_cursorIndexOfEnlaceNoticia)) {
              _tmpEnlaceNoticia = null;
            } else {
              _tmpEnlaceNoticia = _cursor.getString(_cursorIndexOfEnlaceNoticia);
            }
            final boolean _tmpRecordatorio;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfRecordatorio);
            _tmpRecordatorio = _tmp != 0;
            final boolean _tmpSincronizado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp_1 != 0;
            _item = new Evento(_tmpId,_tmpTitulo,_tmpDescripcion,_tmpFechaInicio,_tmpFechaFin,_tmpHoraInicio,_tmpHoraFin,_tmpUbicacion,_tmpTipo,_tmpColor,_tmpImagen,_tmpImagenLocal,_tmpEnlaceNoticia,_tmpRecordatorio,_tmpSincronizado);
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
  public Flow<List<Evento>> getEventosByTipo(final String tipo) {
    final String _sql = "SELECT * FROM eventos WHERE tipo = ? ORDER BY fechaInicio";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, tipo);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"eventos"}, new Callable<List<Evento>>() {
      @Override
      @NonNull
      public List<Evento> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfFechaInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaInicio");
          final int _cursorIndexOfFechaFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaFin");
          final int _cursorIndexOfHoraInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "horaInicio");
          final int _cursorIndexOfHoraFin = CursorUtil.getColumnIndexOrThrow(_cursor, "horaFin");
          final int _cursorIndexOfUbicacion = CursorUtil.getColumnIndexOrThrow(_cursor, "ubicacion");
          final int _cursorIndexOfTipo = CursorUtil.getColumnIndexOrThrow(_cursor, "tipo");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfImagen = CursorUtil.getColumnIndexOrThrow(_cursor, "imagen");
          final int _cursorIndexOfImagenLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenLocal");
          final int _cursorIndexOfEnlaceNoticia = CursorUtil.getColumnIndexOrThrow(_cursor, "enlaceNoticia");
          final int _cursorIndexOfRecordatorio = CursorUtil.getColumnIndexOrThrow(_cursor, "recordatorio");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final List<Evento> _result = new ArrayList<Evento>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Evento _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpFechaInicio;
            _tmpFechaInicio = _cursor.getString(_cursorIndexOfFechaInicio);
            final String _tmpFechaFin;
            if (_cursor.isNull(_cursorIndexOfFechaFin)) {
              _tmpFechaFin = null;
            } else {
              _tmpFechaFin = _cursor.getString(_cursorIndexOfFechaFin);
            }
            final String _tmpHoraInicio;
            if (_cursor.isNull(_cursorIndexOfHoraInicio)) {
              _tmpHoraInicio = null;
            } else {
              _tmpHoraInicio = _cursor.getString(_cursorIndexOfHoraInicio);
            }
            final String _tmpHoraFin;
            if (_cursor.isNull(_cursorIndexOfHoraFin)) {
              _tmpHoraFin = null;
            } else {
              _tmpHoraFin = _cursor.getString(_cursorIndexOfHoraFin);
            }
            final String _tmpUbicacion;
            _tmpUbicacion = _cursor.getString(_cursorIndexOfUbicacion);
            final String _tmpTipo;
            _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            final String _tmpImagen;
            if (_cursor.isNull(_cursorIndexOfImagen)) {
              _tmpImagen = null;
            } else {
              _tmpImagen = _cursor.getString(_cursorIndexOfImagen);
            }
            final String _tmpImagenLocal;
            if (_cursor.isNull(_cursorIndexOfImagenLocal)) {
              _tmpImagenLocal = null;
            } else {
              _tmpImagenLocal = _cursor.getString(_cursorIndexOfImagenLocal);
            }
            final String _tmpEnlaceNoticia;
            if (_cursor.isNull(_cursorIndexOfEnlaceNoticia)) {
              _tmpEnlaceNoticia = null;
            } else {
              _tmpEnlaceNoticia = _cursor.getString(_cursorIndexOfEnlaceNoticia);
            }
            final boolean _tmpRecordatorio;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfRecordatorio);
            _tmpRecordatorio = _tmp != 0;
            final boolean _tmpSincronizado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp_1 != 0;
            _item = new Evento(_tmpId,_tmpTitulo,_tmpDescripcion,_tmpFechaInicio,_tmpFechaFin,_tmpHoraInicio,_tmpHoraFin,_tmpUbicacion,_tmpTipo,_tmpColor,_tmpImagen,_tmpImagenLocal,_tmpEnlaceNoticia,_tmpRecordatorio,_tmpSincronizado);
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
  public Object getEventoById(final String id, final Continuation<? super Evento> $completion) {
    final String _sql = "SELECT * FROM eventos WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Evento>() {
      @Override
      @Nullable
      public Evento call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfFechaInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaInicio");
          final int _cursorIndexOfFechaFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaFin");
          final int _cursorIndexOfHoraInicio = CursorUtil.getColumnIndexOrThrow(_cursor, "horaInicio");
          final int _cursorIndexOfHoraFin = CursorUtil.getColumnIndexOrThrow(_cursor, "horaFin");
          final int _cursorIndexOfUbicacion = CursorUtil.getColumnIndexOrThrow(_cursor, "ubicacion");
          final int _cursorIndexOfTipo = CursorUtil.getColumnIndexOrThrow(_cursor, "tipo");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfImagen = CursorUtil.getColumnIndexOrThrow(_cursor, "imagen");
          final int _cursorIndexOfImagenLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenLocal");
          final int _cursorIndexOfEnlaceNoticia = CursorUtil.getColumnIndexOrThrow(_cursor, "enlaceNoticia");
          final int _cursorIndexOfRecordatorio = CursorUtil.getColumnIndexOrThrow(_cursor, "recordatorio");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final Evento _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpFechaInicio;
            _tmpFechaInicio = _cursor.getString(_cursorIndexOfFechaInicio);
            final String _tmpFechaFin;
            if (_cursor.isNull(_cursorIndexOfFechaFin)) {
              _tmpFechaFin = null;
            } else {
              _tmpFechaFin = _cursor.getString(_cursorIndexOfFechaFin);
            }
            final String _tmpHoraInicio;
            if (_cursor.isNull(_cursorIndexOfHoraInicio)) {
              _tmpHoraInicio = null;
            } else {
              _tmpHoraInicio = _cursor.getString(_cursorIndexOfHoraInicio);
            }
            final String _tmpHoraFin;
            if (_cursor.isNull(_cursorIndexOfHoraFin)) {
              _tmpHoraFin = null;
            } else {
              _tmpHoraFin = _cursor.getString(_cursorIndexOfHoraFin);
            }
            final String _tmpUbicacion;
            _tmpUbicacion = _cursor.getString(_cursorIndexOfUbicacion);
            final String _tmpTipo;
            _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            final String _tmpImagen;
            if (_cursor.isNull(_cursorIndexOfImagen)) {
              _tmpImagen = null;
            } else {
              _tmpImagen = _cursor.getString(_cursorIndexOfImagen);
            }
            final String _tmpImagenLocal;
            if (_cursor.isNull(_cursorIndexOfImagenLocal)) {
              _tmpImagenLocal = null;
            } else {
              _tmpImagenLocal = _cursor.getString(_cursorIndexOfImagenLocal);
            }
            final String _tmpEnlaceNoticia;
            if (_cursor.isNull(_cursorIndexOfEnlaceNoticia)) {
              _tmpEnlaceNoticia = null;
            } else {
              _tmpEnlaceNoticia = _cursor.getString(_cursorIndexOfEnlaceNoticia);
            }
            final boolean _tmpRecordatorio;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfRecordatorio);
            _tmpRecordatorio = _tmp != 0;
            final boolean _tmpSincronizado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp_1 != 0;
            _result = new Evento(_tmpId,_tmpTitulo,_tmpDescripcion,_tmpFechaInicio,_tmpFechaFin,_tmpHoraInicio,_tmpHoraFin,_tmpUbicacion,_tmpTipo,_tmpColor,_tmpImagen,_tmpImagenLocal,_tmpEnlaceNoticia,_tmpRecordatorio,_tmpSincronizado);
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
  public Flow<List<String>> getFechasConEventos() {
    final String _sql = "SELECT DISTINCT fechaInicio FROM eventos ORDER BY fechaInicio";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"eventos"}, new Callable<List<String>>() {
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
