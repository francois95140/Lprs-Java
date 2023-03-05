-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 06 fév. 2023 à 10:36
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projet_lprs_java`
--

-- --------------------------------------------------------

--
-- Structure de la table `concerne`
--

DROP TABLE IF EXISTS `concerne`;
CREATE TABLE IF NOT EXISTS `concerne` (
  `ref_fiche_fourniture` int(11) NOT NULL,
  `ref_demande` int(11) NOT NULL,
  KEY `fk_concerne_demande` (`ref_demande`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demande_fournitures`
--

DROP TABLE IF EXISTS `demande_fournitures`;
CREATE TABLE IF NOT EXISTS `demande_fournitures` (
  `id_demande` int(11) NOT NULL AUTO_INCREMENT,
  `nom_fourniture` varchar(30) NOT NULL,
  `quantite_demander` int(40) NOT NULL,
  `ref_prof` int(11) NOT NULL,
  `ref_gestionaire` int(11) NULL,
  PRIMARY KEY (`id_demande`),
  KEY `fk_demande_prof` (`ref_prof`),
  KEY `fk_demande_prof` (`ref_gestionaire`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `dossier_inscription`
--

DROP TABLE IF EXISTS `dossier_inscription`;
CREATE TABLE IF NOT EXISTS `dossier_inscription` (
  `id_dossier` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(8) NOT NULL,
  `heure` varchar(40) NOT NULL,
  `filiere` varchar(40) NOT NULL,
  `motivation` varchar(40) NOT NULL,
  `ref_fiche` int(11) NOT NULL,
  PRIMARY KEY (`id_dossier`),
  KEY `fk_dossier_etudiant` (`ref_fiche`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `est_lie`
--

DROP TABLE IF EXISTS `est_lie`;
CREATE TABLE IF NOT EXISTS `est_lie` (
  `ref_fiche_fourniture` int(11) NOT NULL,
  `ref_fourniture` int(11) NOT NULL,
  KEY `fk_lie_fourniture` (`ref_fourniture`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fiche_etudiant`
--

DROP TABLE IF EXISTS `fiche_etudiant`;
CREATE TABLE IF NOT EXISTS `fiche_etudiant` (
  `id_fiche` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `diplome` varchar(30) NOT NULL,
  `telephone` int(10) NOT NULL,
  `rue` varchar(30) NOT NULL,
  `cp` int(5) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `ref_utilisateur` int(11) NOT NULL,
  PRIMARY KEY (`id_fiche`),
  KEY `fk_etudiant_user` (`ref_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fiche_fourniture`
--

DROP TABLE IF EXISTS `fiche_fourniture`;
CREATE TABLE IF NOT EXISTS `fiche_fourniture` (
  `id_fiche_fourniture` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `prix` varchar(20) NOT NULL,
  `quantite_fourniture` int(30) NOT NULL,
  PRIMARY KEY (`id_fiche_fourniture`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fourniture`
--

DROP TABLE IF EXISTS `fourniture`;
CREATE TABLE IF NOT EXISTS `fourniture` (
  `id_fourniture` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `stock` int(20) NOT NULL,
  `fourniseur` varchar(20) NOT NULL,
  PRIMARY KEY (`id_fourniture`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rendez-vous`
--

DROP TABLE IF EXISTS `rendez-vous`;
CREATE TABLE IF NOT EXISTS `rendez-vous` (
  `id_rdv` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `heure` time NOT NULL,
  `ref_salle` int(3) NOT NULL,
  `ref_prof` int(11) NOT NULL,
  `ref_dossier` int(11) NOT NULL,
  PRIMARY KEY (`id_rdv`),
  KEY `fk_rdv_prof` (`ref_prof`),
  KEY `fk-rdv_salle` (`ref_salle`),
  KEY `fk-rdv_dossier` (`ref_dossier`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `id_salle` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(23) NOT NULL,
  `Batiment` varchar(20) NOT NULL,
  PRIMARY KEY (`id_salle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `mdp` varchar(15) NOT NULL,
  `role` tinyint(3) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `nom`, `prenom`, `email`, `mdp`, `role`) VALUES
(1, 'Louisy', 'arnaud', 'a.louisy@lprs.fr', '', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `demande_fournitures`
--

ALTER TABLE `demande_fournitures`
  ADD CONSTRAINT `fk_demande_prof` FOREIGN KEY (`ref_prof`) REFERENCES `utilisateur` (`id_user`),
  ADD CONSTRAINT `fk_demande_gestionaire` FOREIGN KEY (`ref_gestionaire`) REFERENCES `utilisateur` (`id_user`);

--
-- Contraintes pour la table `dossier_inscription`
--

ALTER TABLE `dossier_inscription`
  ADD CONSTRAINT `fk_dossier_etudiant` FOREIGN KEY (`ref_fiche`) REFERENCES `fiche_etudiant` (`id_fiche`);

--
-- Contraintes pour la table `fiche_etudiant`
--
ALTER TABLE `fiche_etudiant`
  ADD CONSTRAINT `fk_etudiant_user` FOREIGN KEY (`ref_utilisateur`) REFERENCES `utilisateur` (`id_user`);

--
-- Contraintes pour la table `rendez-vous`
--
ALTER TABLE `rendez-vous`
  ADD CONSTRAINT `fk-rdv_salle` FOREIGN KEY (`ref_salle`) REFERENCES `salle` (`id_salle`),
  ADD CONSTRAINT `fk-rdv_dossier` FOREIGN KEY (`ref_dossier`) REFERENCES `dossier_inscription` (`id_dossier`),
  ADD CONSTRAINT `fk_rdv_prof` FOREIGN KEY (`ref_prof`) REFERENCES `utilisateur` (`id_user`);

ALTER TABLE `utilisateur`
    ADD CONSTRAINT `fk_utilisateur_admin` FOREIGN KEY (`ref_admin`) REFERENCES `utilisateur`  (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
