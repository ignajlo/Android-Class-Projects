# Użyj oficjalnego obrazu Ubuntu 22.04
FROM ubuntu:22.04

# Ustaw zmienną DEBIAN_FRONTEND na noninteractive
ENV DEBIAN_FRONTEND=noninteractive

# Aktualizuj paczki i zainstaluj narzędzia
RUN apt-get update && apt-get install -y software-properties-common

# Dodaj repozytorium deadsnakes PPA
RUN add-apt-repository ppa:deadsnakes/ppa

# Aktualizuj paczki ponownie
RUN apt-get update

# Zainstaluj Python 3.8
RUN apt-get install -y python3.8

# Zainstaluj OpenJDK 8 (Java 8)
RUN apt-get install -y openjdk-8-jre

# Zainstaluj Kotlin
RUN apt-get install -y kotlin

# Opcjonalnie: Ustaw Kotlin jako zmienną środowiskową
ENV KOTLIN_HOME /usr/bin/kotlin

# Skonfiguruj zmienną PATH, aby zawierała Kotlin
ENV PATH $PATH:$KOTLIN_HOME/bin

# Opcjonalnie: Wyczyść zbędne pliki
RUN apt-get clean

# Opcjonalnie: Usuń wszystkie niepotrzebne zależności
RUN apt-get autoremove -y

# Opcjonalnie: Usuń listy pakietów
RUN rm -rf /var/lib/apt/lists/*

# Opcjonalnie: Ustaw domyślną lokalizację na /root
WORKDIR /root

# Opcjonalnie: Uruchom powłokę bash
CMD ["/bin/bash"]
