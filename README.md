# AppFede
```Remove-Item -Recurse -Force .gradle```

```$env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"```

```.\gradlew.bat clean```



# 1. Matar cualquier proceso de Java/Gradle que esté bloqueando carpetas
Stop-Process -Name "java" -Force -ErrorAction SilentlyContinue; 

# 2. Borrar las carpetas de caché corruptas (Ahora sí que sí)
Remove-Item -Recurse -Force .gradle; 
Remove-Item -Recurse -Force build; 

# 3. Configurar Java y limpiar
$env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"; 
.\gradlew.bat clean
