# Neohack Project

## Работа с git

Для работы над проектом, вам понадобится установить систему контроля версий git.

1. Установите git согласно инструкции. [Ссылка на инструкцию.](https://git-scm.com/book/ru/v2/%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0-Git)

2. Скопируйте (склонируйте) проект в рабочую папку на компьютере. Есть 2 возможных способа копирования: с помощью HTTPS и SSH. Для клонирования с использованием протокола HTTPS используйте логин и пароль от выданного репозитория. Для клонирования с помощью SSH, установите SSH ключи. 
Пример клонирования с помощью SSH можно [найти по ссылке](https://itisgood.ru/2021/10/14/kak-klonirovat-git-repozitorij-v-opredelennuju-papku/)
Инструкцию по работе с SSH ключами можно найти [тут](https://docs.gitlab.com/ee/user/ssh.html#generate-an-ssh-key-pair)

3. Добавьте необходимые файлы в папку с проектом.

4. Сохраните изменения в папке командой `git commit -a -m"My first commit"`

5. Отправьте сохраненные изменения в репозиторий gitlab с помощью команды `git push`

Больше команд по работе с git вы можете узнать из [шпаргалки](https://training.github.com/downloads/ru/github-git-cheat-sheet/) 

## Сборка и деплой приложения

Для работы вашего приложения, вам выделены ресурсы в облачной инфраструктуре Managed kubernetes. Так же, если вы захотите воспользоваться Gitlab CI/CD, вы можете настроить свой пайплайн. Мы выделили сборщик проектов, который следит за появлением новых заданий в разделе Jobs.

Ваши приложения должны разворачиваться на домене, типа {MYNAME}.neohack2022.codenrock.com, другие варианты работать не будут.

Доступные переменные:
* KUBE_URL - IP kubernetes cluster
* DOMAIN - общий домен (neohack2022.codenrock.com)
* CI_BUILD_TOKEN и CI_REGISTRY - встроенные переменные Gitlab для доступа к Container Registry

Сборка с помощью Gitlab CI/CD происходит по принципу Docker in Docker

## Подключение к kubernetes cluster

В качестве облачной инфраструктуры, вам выделен namespace в kubernetes cluster. Для подключения, воспользуйтесь клиентом kubectl и выданными доступами. Доступы даются на команду. Если у вас нет доступов, свяжитесь с организатором.

Конфиг подключения:
- kubectl config set-cluster k8s --server="$KUBE_URL" --insecure-skip-tls-verify=true
- kubectl config set-credentials "$K8S_USER" --token="$KUBE_TOKEN"
- kubectl config set-context default --cluster=k8s --user="$K8S_USER"
- kubectl config use-context default

* KUBE_TOKEN и K8S_USER выдадут организаторы на команду
## Сертификат SSL

Для установки сертификатов SSL на ваше приложение, вы можете воспользоваться предустановленным Issuer, работающим с letsencrypt.
Пример работы сертификатом и ingress-контроллером указан ниже.

```
apiVersion: cert-manager.io/v1
kind: Certificate
metadata:
    name: ""
    namespace: ""
spec:
    issuerRef:
        group: cert-manager.io
        kind: ClusterIssuer
        name: letsencrypt
    dnsNames:
        - ""
    secretName: ""
---
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
    name: ""
    namespace: ""
    annotations:
        cert-manager.io/clusterissuer: letsencrypt
        kubernetes.io/ingress.class: nginx
spec:
  tls:
    - hosts:
        - ..
      secretName: ""
  rules:
    - host: ""
      http:
        paths:
```

## Jupiter для Data-инженеров

Для работы Data-инженеров мы подготовили Jupiter.

Вход в  Jupiter находится по ссылке [http://jupiter.neohack2022.codenrock.com](http://jupiter.neohack2022.codenrock.com)

Для авторизации используйте логин и пароль от выданного репозитория Gitlab.

## Данные для Data-инженеров

В рамках задачи, вам предлагается проанализировать определённый набор данных. Часть из них вы можете найти в описании задачи (ссылки на .csv и .json файлы), а часть расположена в отдельной базе данных Postgres. Доступ к базе вы можете получить у организаторов. Так же, эту базу данных можно использовать для логирования и других нужд.


