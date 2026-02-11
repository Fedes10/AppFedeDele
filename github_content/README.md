# Contenido de la App DeleFede

Este repositorio contiene el contenido dinámico de la aplicación de la Delegación Diocesana de Juventud de Córdoba.

**Repositorio destino:** https://github.com/Fedes10/AppFede

## Cómo subir contenido al repositorio

### 1. Clonar el repositorio
```bash
git clone https://github.com/Fedes10/AppFede.git
cd AppFede
```

### 2. Copiar esta carpeta
Copia todo el contenido de `github_content/` a la raíz del repositorio clonado.

### 3. Añadir imágenes y audio
- Coloca las imágenes en las carpetas `images/` correspondientes
- Coloca los archivos de audio en `canciones/audio/`

### 4. Subir cambios
```bash
git add .
git commit -m "Actualizar contenido de la app"
git push
```

## Estructura de carpetas

```
/
├── noticias/
│   ├── noticias.json          # Lista de todas las noticias
│   └── images/                # Imágenes de las noticias
│       ├── noticia-001.jpg
│       └── ...
│
├── canciones/
│   ├── canciones.json         # Lista de todas las canciones
│   └── audio/                 # Archivos de audio (MP3)
│       ├── cancion-001.mp3
│       └── ...
│
├── peregrinaciones/
│   ├── peregrinaciones.json   # Lista de peregrinaciones
│   └── images/                # Imágenes de las peregrinaciones
│       ├── fatima-2025.jpg
│       └── ...
│
└── eventos/
    ├── eventos.json           # Lista de eventos del calendario
    └── images/                # Imágenes de eventos (opcional)
```

## Formatos de archivos JSON

### noticias.json
```json
{
  "version": "1.0",
  "ultima_actualizacion": "2026-02-10T10:00:00Z",
  "noticias": [
    {
      "id": "noticia-001",
      "titulo": "Título de la noticia",
      "descripcion": "Breve descripción",
      "contenido": "Contenido completo de la noticia...",
      "imagen": "noticias/images/noticia-001.jpg",
      "categoria": "General",
      "fecha": "2026-02-10",
      "autor": "Delegación de Juventud"
    }
  ]
}
```

### canciones.json
```json
{
  "version": "1.0",
  "ultima_actualizacion": "2026-02-10T10:00:00Z",
  "canciones": [
    {
      "id": "cancion-001",
      "titulo": "Nombre de la canción",
      "artista": "La Dele",
      "categoria": "La Dele",
      "letra": "Letra completa de la canción...",
      "audio": "canciones/audio/cancion-001.mp3",
      "duracion": 245
    }
  ]
}
```

### peregrinaciones.json
```json
{
  "version": "1.0",
  "ultima_actualizacion": "2026-02-10T10:00:00Z",
  "peregrinaciones": [
    {
      "id": "fatima-2025",
      "nombre": "Peregrinación a Fátima 2025",
      "descripcion": "Descripción de la peregrinación...",
      "fecha_salida": "2025-02-20",
      "fecha_regreso": "2025-02-22",
      "precio": 150.0,
      "requisitos": ["+18 años", "DNI vigente"],
      "inscripcion_apertura": "2025-01-01",
      "inscripcion_cierre": "2025-02-13",
      "plazas": 50,
      "enlace_inscripcion": "https://forms.gle/xxx",
      "imagen_portada": "peregrinaciones/images/fatima-2025.jpg",
      "color_tema": "#2196F3"
    }
  ]
}
```

### eventos.json
```json
{
  "version": "1.0",
  "ultima_actualizacion": "2026-02-10T10:00:00Z",
  "eventos": [
    {
      "id": "evento-001",
      "titulo": "Nombre del evento",
      "descripcion": "Descripción del evento",
      "fecha_inicio": "2026-02-20",
      "fecha_fin": "2026-02-22",
      "hora_inicio": "18:00",
      "hora_fin": "21:00",
      "ubicacion": "Catedral de Córdoba",
      "tipo": "convivencia",
      "color": "#FF8A50",
      "imagen": "eventos/images/evento-001.jpg"
    }
  ]
}
```

## Categorías disponibles

### Canciones
- `La Dele` - Canciones propias de la Delegación
- `Hakuna` - Canciones del movimiento Hakuna
- `Adoración` - Canciones de adoración eucarística
- `Mariano` - Canciones marianas
- `Tradicional` - Canciones tradicionales católicas
- `Celebración` - Canciones para celebraciones

### Tipos de eventos
- `peregrinacion` - Peregrinaciones
- `vigilia` - Vigilias y horas santas
- `procesion` - Procesiones
- `convivencia` - Convivencias y encuentros
- `formacion` - Formación y catequesis
- `general` - Otros eventos

## Colores de tema

### Peregrinaciones
- Fátima: `#2196F3` (Azul)
- Guadalupe: `#4CAF50` (Verde)
- Inmaculada: `#9C27B0` (Morado)

### Eventos
- General: `#FF8A50` (Naranja)
- Vigilia: `#9C27B0` (Morado)
- Formación: `#2196F3` (Azul)

## Añadir nuevo contenido

### Nueva noticia
1. Añadir la imagen a `noticias/images/` (formato JPG, max 1200x800px)
2. Añadir entrada al JSON `noticias/noticias.json`
3. Actualizar `ultima_actualizacion`

### Nueva canción
1. Añadir el audio a `canciones/audio/` (formato MP3, max 10MB)
2. Añadir entrada al JSON `canciones/canciones.json`
3. Incluir la letra completa en el campo `letra`
4. Indicar duración en segundos

### Nueva peregrinación
1. Añadir imagen de portada a `peregrinaciones/images/`
2. Añadir entrada al JSON `peregrinaciones/peregrinaciones.json`
3. Configurar fechas de inscripción y enlace al formulario

### Nuevo evento
1. (Opcional) Añadir imagen a `eventos/images/`
2. Añadir entrada al JSON `eventos/eventos.json`
3. Especificar fechas en formato `YYYY-MM-DD`

## Recomendaciones de compresión

### Imágenes
- JPG para fotos, PNG para gráficos
- Resolución máxima: 1200x800px para portadas, 800x600px para miniaturas
- Calidad: 80-85%
- Herramientas: TinyPNG, Squoosh

### Audio
- Formato: MP3
- Bitrate: 128kbps (para canciones normales), 192kbps (alta calidad)
- Tamaño máximo recomendado: 10MB por archivo
- Herramientas: Audacity, FFmpeg

---

Desarrollado con ❤️ para la Delegación Diocesana de Juventud de Córdoba
