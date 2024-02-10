# Mock Exam1 정리

## 노드 리스트 Json Format으로 출력하기
- 아래와 같이 -o json 옵션을 통하여 json format으로 출력
```shell
kubectl get nodes -o json > /opt/outputs/nodes.json
```

## No 11. node 리스트 중 osImage 값 출력하기
- 아래와 같이 -o jsonpath 옵션을 통하여 osImage 값의 json key를 따라서 출력
```shell
kubectl get nodes -o jsonpath='{.items[*].status.nodeInfo.osImage}' > /opt/outputs/nodes_os.txt
```

# Mock Exam2 정리
## No1
- /etc/kubernetes/manifests/etcd.yaml에서 백업에 필요한 key 파일 정보 확인

## No2
```yaml
spec:
  containers:
    volumeMounts:
    - mountPath: /data/redis
      name: cache-volume
  volumes:
  - name: cache-volume
    emptyDir: {}
```

## No3
```yaml
spec:
  containers:
    command: ["sleep","4800"]
    securityContext:
      capabilities:
        add: ["SYS_TIME"]
```

## No4
- pvc 생성
```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: my-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 10Mi
```
- pod yaml 파일 내에 pvc 내용 추가
```yaml
    volumeMounts:
      - mountPath: "/data"
        name: mypd
  volumes:
    - name: mypd
      persistentVolumeClaim:
        claimName: my-pvc
```

## No5
- deployment 생성: k create deployment nginx-deploy --image=nginx:1.16 --replicas=1
- 1,17 버전으로 업데이트: k set image deployment/nginx-deploy nginx=nginx:1.17

## No6
- csr 생성: name - john-developer
```yaml
apiVersion: certificates.k8s.io/v1
kind: CertificateSigningRequest
metadata:
  name: myuser
spec:
  request: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURSBSRVFVRVNULS0tLS0KTUlJQ1ZqQ0NBVDRDQVFBd0VURVBNQTBHQTFVRUF3d0dZVzVuWld4aE1JSUJJakFOQmdrcWhraUc5dzBCQVFFRgpBQU9DQVE4QU1JSUJDZ0tDQVFFQTByczhJTHRHdTYxakx2dHhWTTJSVlRWMDNHWlJTWWw0dWluVWo4RElaWjBOCnR2MUZtRVFSd3VoaUZsOFEzcWl0Qm0wMUFSMkNJVXBGd2ZzSjZ4MXF3ckJzVkhZbGlBNVhwRVpZM3ExcGswSDQKM3Z3aGJlK1o2MVNrVHF5SVBYUUwrTWM5T1Nsbm0xb0R2N0NtSkZNMUlMRVI3QTVGZnZKOEdFRjJ6dHBoaUlFMwpub1dtdHNZb3JuT2wzc2lHQ2ZGZzR4Zmd4eW8ybmlneFNVekl1bXNnVm9PM2ttT0x1RVF6cXpkakJ3TFJXbWlECklmMXBMWnoyalVnald4UkhCM1gyWnVVV1d1T09PZnpXM01LaE8ybHEvZi9DdS8wYk83c0x0MCt3U2ZMSU91TFcKcW90blZtRmxMMytqTy82WDNDKzBERHk5aUtwbXJjVDBnWGZLemE1dHJRSURBUUFCb0FBd0RRWUpLb1pJaHZjTgpBUUVMQlFBRGdnRUJBR05WdmVIOGR4ZzNvK21VeVRkbmFjVmQ1N24zSkExdnZEU1JWREkyQTZ1eXN3ZFp1L1BVCkkwZXpZWFV0RVNnSk1IRmQycVVNMjNuNVJsSXJ3R0xuUXFISUh5VStWWHhsdnZsRnpNOVpEWllSTmU3QlJvYXgKQVlEdUI5STZXT3FYbkFvczFqRmxNUG5NbFpqdU5kSGxpT1BjTU1oNndLaTZzZFhpVStHYTJ2RUVLY01jSVUyRgpvU2djUWdMYTk0aEpacGk3ZnNMdm1OQUxoT045UHdNMGM1dVJVejV4T0dGMUtCbWRSeEgvbUNOS2JKYjFRQm1HCkkwYitEUEdaTktXTU0xMzhIQXdoV0tkNjVoVHdYOWl4V3ZHMkh4TG1WQzg0L1BHT0tWQW9FNkpsYWFHdTlQVmkKdjlOSjVaZlZrcXdCd0hKbzZXdk9xVlA3SVFjZmg3d0drWm89Ci0tLS0tRU5EIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLQo=
  signerName: kubernetes.io/kube-apiserver-client
  expirationSeconds: 86400  # one day
  usages:
  - client auth
```
- csr 승인: k certificate approve john-developer
- role 생성: k create role developer --verb=create,get,list,update,delete --resource=pods -n development
- 권한 확인: k auth can-i create pods --namespace=development --as john, k auth can-i get pods --namespace=development --as john
- rolebinding 생성: k create rolebinding john-developer --role=developer --user=john -n development
- 다시 권한 확인: k auth can-i create pods --namespace=development --as john, k auth can-i get pods --namespace=development --as john

## No7
- nginx-resolver pod 생성: k run nginx-resolver --image=nginx
- svc 생성: k expose pod nginx-resolver --name=nginx-resolver-service --port=80
- busybox pod 생성: k run busybox --image=busybox:1.28 -- sleep 4000
- k exec busybox -- nslookup nginx-resolver-service > /root/CKA/nginx.svc
- k exec busybox -- nslookup 10-50-192.4.default.pod.cluster.local > /root/CKA/nginx.pod

## No8
- kubectl run nginx-critical --image=nginx --restart=Always --dry-run=client -o yaml로 생성된 yaml 내용을 node01에 /etc/kubernetes/manifests/nginx-critical.yaml에 추가

# Mock Exam3 정리
## No1
- kubectl create serviceaccount pvviewer로 서비스 어카운트 생성
- kubectl create clusterrole pvviewer-role --verb=list --resource=persistentvolumes로 클러스터롤 생성
- kubectl create clusterrolebinding pvviewer-role-binding --clusterrole=pvviewer-role --serviceaccount=default:pvviewer
- k run pvviewer --image=redis --dry-run -o yaml로 yaml 파일 생성 후 serviceAccountName 추가

## No2
```shell
k get nodes -o jsonpath='{.items[*].status.addresses[?(@.type=="InternalIP")].address}' > /root/CKA/node_ips
```

## No3 - MultiPod
-  k run 명령어로 yaml 파일 생성
- 이후 multipad에 대한 스크립트 추가
```yaml
apiVersion: v1
kind: Pod
spec:
  containers:
  - image: nginx
    name: alpha
    env:
      - name: "name"
        value: "alpha"
  - image: busybox
    name: beta
    command:
      - sleep
      - "4800"
    env:
      - name: "name"
        value: "beta"
```

## No4
- securityContext에 runAsUser와 fsGroup 추가
```yaml
spec:
  securityContext:
    runAsUser: 1000
    fsGroup: 2000
```

## No5
- k run curl --image=alpine/curl --rm -it -- sh
```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: ingress-to-nptest
  namespace: default
spec:
  podSelector:
    matchLabels:
      run: np-test-1
  policyTypes:
    - Ingress
  ingress:
    -
      ports:
        - protocol: TCP
          port: 80
```

## No6
- kubectl taint nodes node01 env_type=production:NoSchedule 테인트 생성
- k run dev-redis --image=redis:alpine 파드 생성
- k run prod-redis --image=redis:alpine --dry-run=client -o yaml > prod-redis.yaml
```yaml
spec:
  tolerations:
  - key: "env_type"
    operator: "Equal"
    value: "production"
    effect: "NoSchedule"
```

## No7
- k create ns hr 네임스페이스 생성
- kubectl run hr-pod -n hr --image=redis:alpine --labels="environment=production,tier=frontend"로 파드 생성

## No8
- kubectl get nodes --kubeconfig /root/CKA/super.kubeconfig로 로그 확인
- cat .kube/config에서 controlplane포트 확인
- super.kubeconfig파일에서 해당 포트에 맞게 수정

## No9
- k scale deployment nginx-deploy --replicas=3으로 스케일업
- kubectl get pods -n kube-system에서 상태 확인
- /etc/kubernetes/manifests/vi kube-controller-manager.yaml에서 오타 확인