
---
## Partie 1 (théorique):

1. La définition de « **Machine virtuelle** »

Une machine virtuelle, également appelée VM, représente un environnement virtuel complet opérant au sein d'une machine physique. Elle opère avec son propre système d’exploitation (OS) et utilise les mêmes composants comme une machine physique. Sur un même serveur physique, plusieurs machines virtuelles peuvent opérer, chacune avec des OS distincts.

2. Les définitions de « **Hyperviseur** » et « **emulateur** » et une comparaison entre les deux.

**Hyperviseur** : Un hyperviseur, également connu sous le nom de "VMM" (Virtual Machine Monitor) ou de "gestionnaire de machine virtuelle", est un logiciel ou une plateforme matérielle qui permet de créer et de gérer des machines virtuelles (VM). Il agit comme une couche logicielle intermédiaire entre le matériel physique d'un ordinateur (serveur, PC, etc.) et les systèmes d'exploitation des machines virtuelles.

**Émulateur** : est un concept visant à créer un environnement qui imite les propriétés d'un système sur un autre, reproduit les caractéristiques et la logique d'un processeur pour fonctionner efficacement sur une autre plateforme, L'émulation est un excellent moyen d'exécuter un système d'exploitation ou un logiciel sur un autre système.

**Comparaison** :

| Aspect             | Emulateur                                                 | Hyperviseur                                                               |
| ------------------ | --------------------------------------------------------- | ------------------------------------------------------------------------- |
| Accès au Hardware  | Besoin d'une application connecteur                       | Direct                                                                    |
| Solution de Backup | Mieux                                                     | Pas assez bon                                                             |
| Cout               | Plus cher                                                 | Moins cher                                                                |
| Vitesse            | Plus rapide                                               | Moins Vite                                                                |
| But                | Réplique le comportement d'un système sur un autre        | Gère plusieurs machines virtuelles sur un seul hôte                       |
| Functionality      | Imite un matériel ou un système d'exploitation spécifique | Alloue et gère les ressources hôte pour les machines virtuelles           |
| Performance        | Peut être gourmand en ressources                          | Overhead exists but more efficient resource use                           |
| Isolation          | Exécute les applications de manière indépendante          | Garantit que les machines virtuelles fonctionnent de manière indépendante |


3. Pour chacun des deux solutions de virtualisation, donner :
	1. **sa définition**,
		1. *Oracle Virtual Box*: Oracle VM VirtualBox est un *hyperviseur de type 2* puissant, gratuit et open-source qui permet aux utilisateurs de créer et d'exécuter plusieurs machines virtuelles sur un seul ordinateur physique. Il autorise l'installation et l'exploitation de différents systèmes d'exploitation simultanément, offrant un environnement isolé pour les tests, le développement ou l'exécution d'applications indépendamment du système hôte. 
		2. *VMWare Esxi*: est un *hyperviseur de classe entreprise de type 1* développé par VMware pour déployer et servir des ordinateurs virtuels. En tant qu'hyperviseur de type 1, ESXi n'est pas une application logicielle installée sur un système d'exploitation (OS) ; à la place, il intègre des composants OS essentiels tels qu'un noyau.
	2. **ses fonctionnalités**
		1. *Oracle Virtual Box*
			1. Création et Configuration de Machines Virtuelles (VM)
			2. Compatibilité Multi-plateforme
			3. Gestion des Instantanés (Snapshots)
			4. Réseau Virtuel
			5. Partage de Dossiers et du Presse-papiers
			6. Support des Périphériques USB
		2. *VMWare Esxi*
			1. Virtualisation au Niveau du Matériel
			2. Isolation et Sécurité
			3. Gestion des Ressources de Haut Niveau
			4. Haut Niveau de Disponibilité
			5. Administration Centralisée
			6. Performances Élevées
			7. scalabilité verticale et horizontale
			8. Gestion des Mises à Jour et de la Sécurité
	3. **ses utilisations**
		1. 1. *Oracle Virtual Box*
			1. Développement de logiciels et tests
			2. Exécution d'applications obsolètes ou malveillantes
			4. Formation et Apprentissage
		2. *VMWare Esxi*
			1. Virtualisation d'Entreprise et de serveurs
			2. Hébergement de Machines Virtuelles

5. Réaliser un bilan et un tableau comparatif des deux solutions en se basant sur des critères tels que : types de virtualisation, OS supportés, sécurité, migration de VM...

| Critères                    | VirtualBox                                                                | VMware ESXi                                                      |
| --------------------------- | ------------------------------------------------------------------------- | ---------------------------------------------------------------- |
| Type de Virtualisation      | Hyperviseur de type 2 (Hébergé)                                           | Hyperviseur de type 1 (Natif)                                    |
| OS Supportés                | Large éventail d'OS invités                                               | Support étendu pour les OS invités                               |
| Sécurité                    | Isolation limitée entre le système hôte et les VM                         | Forte isolation, sécurité avancée                                |
| Migration de VM             | Migration limitée, pas aussi robuste pour les déploiements professionnels | Capacités avancées de migration et de gestion des VM             |
| Gestion d'Entreprise        | Moins adapté pour des déploiements à grande échelle                       | Conçu pour la virtualisation en entreprise, gestion avancée      |
| Maintenance et Mises à Jour | Plus facile à maintenir et à mettre à jour pour un usage individuel       | Outils avancés de gestion des correctifs et des mises à jour     |
| Facilité d'Utilisation      | Interface utilisateur conviviale, idéale pour un usage personnel          | Peut être plus complexe, mais offre des fonctionnalités avancées |
| Performances                | moins efficace pour les charges lourdes                                   | Optimisé pour les environnements de production                   |

---
## Partie 2 : (les deux sous parties sont indépendantes)

### I. Création et manipulation des VMs avec Oracle Virtual Box :

1. Duplication de VM Windows 7.

[Image](/Images/Pasted image 20231127214012.png)

2. Partage de fichiers entre la VM et l'hôte.
	* Après l'installation de Virtual Box guest tools 
	  ![[Pasted image 20231127214722.png | 700]]
	* `Settings > Shared Folders >` 
	  ![[Pasted image 20231127214224.png | 700]]
1. Partager des périphériques entre la VM et l'hôte.
	* *+ Pour Linux* : Ajoute le USER local dans le groupe `vboxusers` : `sudo gpasswd -a $USER vboxusers`
	* `Settings > USB > USB+` : Ajouter un périphérique 
	  ![[Pasted image 20231127220210.png | 700]]
1. Création et restauration d'une image instantanée pour la VM Windows 7.
	* Création : `Machine > Take Snapshot` 
	  ![[Pasted image 20231127220634.png | 700]]
	* Restauration : 
	  
	  ![[Pasted image 20231127221225.png | 700]]

5. Mettre les VM dans un réseau privé.
	* `Settings > Network > Attached to ` > **Host only**
6. Mettre les VM sur le même réseau que l'hôte.
	* `Settings > Network > Attached to ` > **NAT**
7. Mettre une VM dans 2 réseaux différents.
	1. Ajouter une deuxième interface réseaux : 
	   ![[Pasted image 20231127222208.png | 700]]
	   ![[Pasted image 20231127222131.png | 700]]
	2. Redémarrer la machine virtuelle


---
### II. Installer un serveur de virtualisation VMWare Esxi

3. Installer Esxi sur la machine serveur, en rebootant sur le CD-ROM
![[Pasted image 20231128135827.png | 500]]
![[Pasted image 20231128135858.png | 500]]
![[Pasted image 20231128135912.png | 500]]

4. Attribuer un mot de passe pour l'utilisateur **root** ainsi qu'une adresse IP pour la machine (ex :192.0.0.1) et préciser l'adresse de la passerelle (gateway)
![[Pasted image 20231128135959.png | 500]]
* 💡 *L'adresse de passerelle sera spécifiée automatiquement* 
5. Connecter au serveur à partir du poste client : *192.168.1.27*
![[Pasted image 20231128140312.png | 700]]
![[Pasted image 20231128135710.png | 700]]
6. Lancer la configuration d’une nouvelle machine virtuelle. Choisir une configuration typique pour la machine virtuelle.

![[1.jpg | 700]]

7. Attribuer un nom évocateur si possible (en rapport avec l'OS ou la fonction du serveur, par exemple) pour la machine virtuelle.
8. Préciser le système d'exploitation qui sera exécuté sur la machine virtuelle. L'intérêt est ici de permettre à VMWare de proposer des paramètres adaptés à l'OS.

![[2.jpg | 700]]

![[3.jpg | 700]]

9. Attribuer suffisamment de RAM pour que l'OS virtualisé tourne confortablement : 2Go semble correct.

![[4.jpg | 700]]

10. Validez la configuration

11. Pour pouvoir installer l'OS de la machine virtuelle, à partir d'une image ISO
![[5.jpg | 700]]

12. Il suffit alors de connecter l'image ISO de l'installateur de notre OS, ici une distribution Ubuntu, après avoir démarré la machine virtuelle.
![[6.jpg | 700]]

