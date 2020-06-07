insert into template (id, date_created, rule_name, template_name, rule_values ) values
('1', '2020-01-01 00:00:00', 'Stop adding water to mash tun', 'phase2-stop_adding_water_to_mash_tun', 'water=70'),
('2', '2020-01-01 00:00:00', 'Stop adding malt to mash tun', 'phase2-stop_adding_malt_to_mash_tun', 'malt=20'),
('3', '2020-01-01 00:00:00', 'Start mashing', 'phase2-start_mashing', 'time=5000'),
('4', '2020-01-01 00:00:00', 'Stop releasing mixture', 'phase3-stop_releasing_mixture', 'mash_tun=30;pre_launter_tun=60'),
('5', '2020-01-01 00:00:00', 'Stop releasing mixture to launter tun', 'phase4-stop_releasing_mixture_to_launter_tun', 'launter_tun=60'),
('6', '2020-01-01 00:00:00', 'Stop adding water to launter tun', 'phase4-stop_adding_water_to_launter_tun', 'launter_tun_water=20'),
('7', '2020-01-01 00:00:00', 'Stop releasing mixture to pre launter tun', 'phase4-stop_releasing_mixture_to_pre_launter_tun', 'pre_launter_tun=60'),
('8', '2020-01-01 00:00:00', 'Stop releasing hops to brew kettle', 'phase5-stop_releasing_hops_to_brew_kettle', 'brew_kettle_hops=10'),
('9', '2020-01-01 00:00:00', 'Start boiling', 'phase5-start_boiling', 'time=5000'),
('10', '2020-01-01 00:00:00', 'Start swirling', 'phase6-start_swirling', 'time=5000'),
('11', '2020-01-01 00:00:00', 'Stop adding yeast', 'phase8-stop_adding_yeast', 'yeast=20'),
('12', '2020-01-01 00:00:00', 'Begin fermentation', 'phase8-begin_fermentation', 'time=10000'),
('13', '2020-01-01 00:00:00', 'Begin beer''s conditioning', 'phase9-begin_beers_conditioning', 'time=5000');
