package com.example.delefede.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile NoticiaDao _noticiaDao;

  private volatile CancionDao _cancionDao;

  private volatile PeregrinacionDao _peregrinacionDao;

  private volatile EventoDao _eventoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `noticias` (`id` TEXT NOT NULL, `titulo` TEXT NOT NULL, `descripcion` TEXT NOT NULL, `contenido` TEXT NOT NULL, `imagenUrl` TEXT NOT NULL, `categoria` TEXT NOT NULL, `fecha` TEXT NOT NULL, `autor` TEXT NOT NULL, `imagenLocal` TEXT, `sincronizado` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `canciones` (`id` TEXT NOT NULL, `titulo` TEXT NOT NULL, `artista` TEXT NOT NULL, `categoria` TEXT NOT NULL, `letra` TEXT NOT NULL, `audioUrl` TEXT NOT NULL, `duracion` INTEGER NOT NULL, `audioLocal` TEXT, `sincronizado` INTEGER NOT NULL, `descargado` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `peregrinaciones` (`id` TEXT NOT NULL, `nombre` TEXT NOT NULL, `descripcion` TEXT NOT NULL, `fechaSalida` TEXT NOT NULL, `fechaRegreso` TEXT NOT NULL, `precio` TEXT NOT NULL, `requisitos` TEXT NOT NULL, `inscripcionApertura` TEXT NOT NULL, `inscripcionCierre` TEXT NOT NULL, `plazas` TEXT NOT NULL, `enlaceInscripcion` TEXT NOT NULL, `imagenPortadaUrl` TEXT NOT NULL, `imagenesUrls` TEXT NOT NULL, `colorTema` TEXT NOT NULL, `imagenPortadaLocal` TEXT, `sincronizado` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `eventos` (`id` TEXT NOT NULL, `titulo` TEXT NOT NULL, `descripcion` TEXT NOT NULL, `fechaInicio` TEXT NOT NULL, `fechaFin` TEXT, `horaInicio` TEXT, `horaFin` TEXT, `ubicacion` TEXT NOT NULL, `tipo` TEXT NOT NULL, `color` TEXT NOT NULL, `imagen` TEXT, `imagenLocal` TEXT, `enlaceNoticia` TEXT, `recordatorio` INTEGER NOT NULL, `sincronizado` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '90a902d03da26abde304740bf5af4f4f')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `noticias`");
        db.execSQL("DROP TABLE IF EXISTS `canciones`");
        db.execSQL("DROP TABLE IF EXISTS `peregrinaciones`");
        db.execSQL("DROP TABLE IF EXISTS `eventos`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsNoticias = new HashMap<String, TableInfo.Column>(10);
        _columnsNoticias.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoticias.put("titulo", new TableInfo.Column("titulo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoticias.put("descripcion", new TableInfo.Column("descripcion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoticias.put("contenido", new TableInfo.Column("contenido", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoticias.put("imagenUrl", new TableInfo.Column("imagenUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoticias.put("categoria", new TableInfo.Column("categoria", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoticias.put("fecha", new TableInfo.Column("fecha", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoticias.put("autor", new TableInfo.Column("autor", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoticias.put("imagenLocal", new TableInfo.Column("imagenLocal", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoticias.put("sincronizado", new TableInfo.Column("sincronizado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNoticias = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNoticias = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNoticias = new TableInfo("noticias", _columnsNoticias, _foreignKeysNoticias, _indicesNoticias);
        final TableInfo _existingNoticias = TableInfo.read(db, "noticias");
        if (!_infoNoticias.equals(_existingNoticias)) {
          return new RoomOpenHelper.ValidationResult(false, "noticias(com.example.delefede.data.model.Noticia).\n"
                  + " Expected:\n" + _infoNoticias + "\n"
                  + " Found:\n" + _existingNoticias);
        }
        final HashMap<String, TableInfo.Column> _columnsCanciones = new HashMap<String, TableInfo.Column>(10);
        _columnsCanciones.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCanciones.put("titulo", new TableInfo.Column("titulo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCanciones.put("artista", new TableInfo.Column("artista", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCanciones.put("categoria", new TableInfo.Column("categoria", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCanciones.put("letra", new TableInfo.Column("letra", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCanciones.put("audioUrl", new TableInfo.Column("audioUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCanciones.put("duracion", new TableInfo.Column("duracion", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCanciones.put("audioLocal", new TableInfo.Column("audioLocal", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCanciones.put("sincronizado", new TableInfo.Column("sincronizado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCanciones.put("descargado", new TableInfo.Column("descargado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCanciones = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCanciones = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCanciones = new TableInfo("canciones", _columnsCanciones, _foreignKeysCanciones, _indicesCanciones);
        final TableInfo _existingCanciones = TableInfo.read(db, "canciones");
        if (!_infoCanciones.equals(_existingCanciones)) {
          return new RoomOpenHelper.ValidationResult(false, "canciones(com.example.delefede.data.model.Cancion).\n"
                  + " Expected:\n" + _infoCanciones + "\n"
                  + " Found:\n" + _existingCanciones);
        }
        final HashMap<String, TableInfo.Column> _columnsPeregrinaciones = new HashMap<String, TableInfo.Column>(16);
        _columnsPeregrinaciones.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("descripcion", new TableInfo.Column("descripcion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("fechaSalida", new TableInfo.Column("fechaSalida", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("fechaRegreso", new TableInfo.Column("fechaRegreso", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("precio", new TableInfo.Column("precio", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("requisitos", new TableInfo.Column("requisitos", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("inscripcionApertura", new TableInfo.Column("inscripcionApertura", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("inscripcionCierre", new TableInfo.Column("inscripcionCierre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("plazas", new TableInfo.Column("plazas", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("enlaceInscripcion", new TableInfo.Column("enlaceInscripcion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("imagenPortadaUrl", new TableInfo.Column("imagenPortadaUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("imagenesUrls", new TableInfo.Column("imagenesUrls", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("colorTema", new TableInfo.Column("colorTema", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("imagenPortadaLocal", new TableInfo.Column("imagenPortadaLocal", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPeregrinaciones.put("sincronizado", new TableInfo.Column("sincronizado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPeregrinaciones = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPeregrinaciones = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPeregrinaciones = new TableInfo("peregrinaciones", _columnsPeregrinaciones, _foreignKeysPeregrinaciones, _indicesPeregrinaciones);
        final TableInfo _existingPeregrinaciones = TableInfo.read(db, "peregrinaciones");
        if (!_infoPeregrinaciones.equals(_existingPeregrinaciones)) {
          return new RoomOpenHelper.ValidationResult(false, "peregrinaciones(com.example.delefede.data.model.Peregrinacion).\n"
                  + " Expected:\n" + _infoPeregrinaciones + "\n"
                  + " Found:\n" + _existingPeregrinaciones);
        }
        final HashMap<String, TableInfo.Column> _columnsEventos = new HashMap<String, TableInfo.Column>(15);
        _columnsEventos.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("titulo", new TableInfo.Column("titulo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("descripcion", new TableInfo.Column("descripcion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("fechaInicio", new TableInfo.Column("fechaInicio", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("fechaFin", new TableInfo.Column("fechaFin", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("horaInicio", new TableInfo.Column("horaInicio", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("horaFin", new TableInfo.Column("horaFin", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("ubicacion", new TableInfo.Column("ubicacion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("tipo", new TableInfo.Column("tipo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("color", new TableInfo.Column("color", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("imagen", new TableInfo.Column("imagen", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("imagenLocal", new TableInfo.Column("imagenLocal", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("enlaceNoticia", new TableInfo.Column("enlaceNoticia", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("recordatorio", new TableInfo.Column("recordatorio", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEventos.put("sincronizado", new TableInfo.Column("sincronizado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEventos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEventos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEventos = new TableInfo("eventos", _columnsEventos, _foreignKeysEventos, _indicesEventos);
        final TableInfo _existingEventos = TableInfo.read(db, "eventos");
        if (!_infoEventos.equals(_existingEventos)) {
          return new RoomOpenHelper.ValidationResult(false, "eventos(com.example.delefede.data.model.Evento).\n"
                  + " Expected:\n" + _infoEventos + "\n"
                  + " Found:\n" + _existingEventos);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "90a902d03da26abde304740bf5af4f4f", "b588c1910732468339eb0d1795859aef");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "noticias","canciones","peregrinaciones","eventos");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `noticias`");
      _db.execSQL("DELETE FROM `canciones`");
      _db.execSQL("DELETE FROM `peregrinaciones`");
      _db.execSQL("DELETE FROM `eventos`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(NoticiaDao.class, NoticiaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CancionDao.class, CancionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PeregrinacionDao.class, PeregrinacionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EventoDao.class, EventoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public NoticiaDao noticiaDao() {
    if (_noticiaDao != null) {
      return _noticiaDao;
    } else {
      synchronized(this) {
        if(_noticiaDao == null) {
          _noticiaDao = new NoticiaDao_Impl(this);
        }
        return _noticiaDao;
      }
    }
  }

  @Override
  public CancionDao cancionDao() {
    if (_cancionDao != null) {
      return _cancionDao;
    } else {
      synchronized(this) {
        if(_cancionDao == null) {
          _cancionDao = new CancionDao_Impl(this);
        }
        return _cancionDao;
      }
    }
  }

  @Override
  public PeregrinacionDao peregrinacionDao() {
    if (_peregrinacionDao != null) {
      return _peregrinacionDao;
    } else {
      synchronized(this) {
        if(_peregrinacionDao == null) {
          _peregrinacionDao = new PeregrinacionDao_Impl(this);
        }
        return _peregrinacionDao;
      }
    }
  }

  @Override
  public EventoDao eventoDao() {
    if (_eventoDao != null) {
      return _eventoDao;
    } else {
      synchronized(this) {
        if(_eventoDao == null) {
          _eventoDao = new EventoDao_Impl(this);
        }
        return _eventoDao;
      }
    }
  }
}
