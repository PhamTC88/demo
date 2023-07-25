DROP TABLE IF EXISTS `ToDoList`;
CREATE TABLE `ToDoList` (
  `id` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `completed` boolean DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `ToDoList` (`id`, `name`, `completed`) VALUES ('todo-3', 'Training', false);